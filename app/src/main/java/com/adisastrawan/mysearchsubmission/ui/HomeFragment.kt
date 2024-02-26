package com.adisastrawan.mysearchsubmission.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.adisastrawan.mysearchsubmission.R
import com.adisastrawan.mysearchsubmission.data.respond.ItemsItem
import com.adisastrawan.mysearchsubmission.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private  var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        binding.rvUsers.layoutManager = layoutManager
        val itemDecorations = DividerItemDecoration(activity, layoutManager.orientation)
        binding.rvUsers.addItemDecoration(itemDecorations)
        val viewModel = MainViewModel()
        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    viewModel.getGithubUsers(searchView.text.toString())
                    false
                }
        }
        viewModel.users.observe(this.viewLifecycleOwner) {
            setGithubUsers(it)
        }
        viewModel.isLoading.observe(this.viewLifecycleOwner){
            showLoading(it)
        }

    }
    private fun showLoading(isLoading : Boolean){
        if(isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    private fun setGithubUsers(users:List<ItemsItem>){
        val adapter = UsersAdapter()
        adapter.submitList(users)
        binding.rvUsers.adapter = adapter
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }
}