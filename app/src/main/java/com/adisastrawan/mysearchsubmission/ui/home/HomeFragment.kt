package com.adisastrawan.mysearchsubmission.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.adisastrawan.mysearchsubmission.data.repository.Result
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserEntity
import com.adisastrawan.mysearchsubmission.data.remote.respond.ItemsItem
import com.adisastrawan.mysearchsubmission.databinding.FragmentHomeBinding
import com.adisastrawan.mysearchsubmission.ui.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {
    private  var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        binding.rvUsers.layoutManager = layoutManager
        val itemDecorations = DividerItemDecoration(activity, layoutManager.orientation)
        binding.rvUsers.addItemDecoration(itemDecorations)
        val factory : ViewModelFactory =ViewModelFactory.getInstance(this.requireActivity())
        val viewModel : MainViewModel by viewModels{ factory }
        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    getUser(searchView.text.toString(),viewModel)
                    false
                }
        }
        getUser(viewModel=viewModel)
        viewModel.snackBarText.observe(this.viewLifecycleOwner){ it->
            it.getContentIfNotHandled()?.let {
                Snackbar.make(  (activity as AppCompatActivity).window.decorView.rootView,
                    it,
                    Snackbar.LENGTH_SHORT).show()
            }
        }

    }
    private fun getUser(username: String= "Adi",viewModel:MainViewModel){
        viewModel.getGithubUsers(username).observe(this.viewLifecycleOwner){ result ->
            if (result != null) {
                Log.d("result",result.toString())
                when (result) {
                    is Result.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        val users = result.data
                        setGithubUsers(users)
                    }
                    is Result.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(
                            context,
                            "Terjadi kesalahan" + result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        }
    }
    private fun setGithubUsers(users:List<UserEntity>){
        val adapter = UsersAdapter()
        adapter.submitList(users)
        binding.rvUsers.adapter = adapter
        if(users.isEmpty()){
            binding.tvExist.visibility =View.VISIBLE
        }else{
            binding.tvExist.visibility =View.INVISIBLE
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }
}