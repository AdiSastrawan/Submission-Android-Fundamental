package com.adisastrawan.mysearchsubmission.ui.detail.follower

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.adisastrawan.mysearchsubmission.data.respond.DetailUserResponse
import com.adisastrawan.mysearchsubmission.databinding.FragmentFollowerBinding
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
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[FollowerViewModel::class.java]
        var position = 0
        var username = ""
        arguments?.let{
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME).toString()
        }
        Log.d("position",position.toString())
        if(position == 1){
            viewModel.getUserFollowers(username)
        }else {
            viewModel.getUserFollowing(username)
        }
        viewModel.isLoading.observe(this.viewLifecycleOwner){
            showLoading(it)
        }
        viewModel.users.observe(this.viewLifecycleOwner){
            setFollower(it)
        }
        viewModel.snackBarText.observe(this.viewLifecycleOwner){ it->
            it.getContentIfNotHandled()?.let {
                Snackbar.make(  (activity as AppCompatActivity).window.decorView.rootView,
                    it,
                    Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading(isLoading : Boolean){
        if(isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    private fun setFollower(users:List<DetailUserResponse>){
        val adapter = FollowerAdapter()
        adapter.submitList(users)
        binding.rvFollower.adapter = adapter
    }
    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME ="username"
    }
}