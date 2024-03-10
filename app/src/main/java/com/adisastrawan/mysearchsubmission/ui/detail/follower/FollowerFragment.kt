package com.adisastrawan.mysearchsubmission.ui.detail.follower

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserFollowerEntity
import com.adisastrawan.mysearchsubmission.data.remote.respond.DetailUserResponse
import com.adisastrawan.mysearchsubmission.data.repository.Result
import com.adisastrawan.mysearchsubmission.databinding.FragmentFollowerBinding
import com.adisastrawan.mysearchsubmission.ui.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class FollowerFragment : Fragment() {
    private var _binding : FragmentFollowerBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        binding.rvFollower.layoutManager = layoutManager
        val itemDecorations = DividerItemDecoration(activity, layoutManager.orientation)
        binding.rvFollower.addItemDecoration(itemDecorations)
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel : FollowerViewModel by viewModels {factory }
        var position = 0
        var username = ""
        arguments?.let{
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME).toString()
        }
        Log.d("position",position.toString())
        if(position == 1){
            Log.d("masih disini", "test")
            viewModel.getUserFollowers(username).observe(viewLifecycleOwner){result ->
               showFollower(result)
            }
        }else {
            viewModel.getUserFollowing(username).observe(viewLifecycleOwner){
                result -> showFollower(result)
            }
        }

        viewModel.snackBarText.observe(this.viewLifecycleOwner){ it->
            it.getContentIfNotHandled()?.let {
                Snackbar.make(  (activity as AppCompatActivity).window.decorView.rootView,
                    it,
                    Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    private fun showFollower(result:Result<List<UserFollowerEntity>>){
        if (result != null) {
            Log.d("result",result.toString())
            when (result) {
                is Result.Loading -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding?.progressBar?.visibility = View.GONE
                    val users = result.data
                    setFollower(users)
                }
                is Result.Error -> {
                    binding?.progressBar?.visibility = View.GONE
                    Toast.makeText(
                        context,
                        "Terjadi kesalahan" + result.error,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setFollower(users:List<UserFollowerEntity>){
        val adapter = FollowerAdapter()
        adapter.submitList(users)
        binding.rvFollower.adapter = adapter
    }
    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME ="username"
    }
}