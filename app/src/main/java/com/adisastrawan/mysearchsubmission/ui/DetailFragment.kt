package com.adisastrawan.mysearchsubmission.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adisastrawan.mysearchsubmission.R
import com.adisastrawan.mysearchsubmission.data.respond.DetailUserResponse
import com.adisastrawan.mysearchsubmission.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide


class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = DetailFragmentArgs.fromBundle(arguments as Bundle).username
        val viewModel = DetailViewModel()
        viewModel.getDetailUser(username)
        viewModel.userDetail.observe(this.viewLifecycleOwner){
            setDetailUser(it)
        }
        viewModel.isLoading.observe(this.viewLifecycleOwner){
            showLoading(it)
        }
    }

    private fun setDetailUser(userDetail:DetailUserResponse) {
        binding.tvUsername.text = userDetail.login
        binding.tvName.text = userDetail.name
        binding.tvFollower.text = if(userDetail.followers > 1) "${userDetail.followers} followers" else "${userDetail.followers} follower"
        binding.tvFollowing.text = "${userDetail.following} following"
        Glide.with(this).load(userDetail.avatarUrl).into(binding.ivAvatar)

    }
    private fun showLoading(isLoading : Boolean){
        if(isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }
}