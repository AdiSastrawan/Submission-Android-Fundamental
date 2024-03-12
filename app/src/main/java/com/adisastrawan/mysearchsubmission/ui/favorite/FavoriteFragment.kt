package com.adisastrawan.mysearchsubmission.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserDetailEntity
import com.adisastrawan.mysearchsubmission.data.repository.Result
import com.adisastrawan.mysearchsubmission.databinding.FragmentFavoriteBinding
import com.adisastrawan.mysearchsubmission.ui.ViewModelFactory

class FavoriteFragment : Fragment() {
    private var _binding : FragmentFavoriteBinding? = null
    private val binding get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        binding.rvUsers.layoutManager = layoutManager
        val itemDecorations = DividerItemDecoration(activity, layoutManager.orientation)
        binding.rvUsers.addItemDecoration(itemDecorations)
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel : FavoriteViewModel by viewModels{factory}
        viewModel.getFavoritedUsers().observe(viewLifecycleOwner){result->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val users = result.data
                        setFavoritedUsers(users)
                    }
                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
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

    private fun setFavoritedUsers(users: List<UserDetailEntity>) {
        val adapter = FavoriteAdapter()
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