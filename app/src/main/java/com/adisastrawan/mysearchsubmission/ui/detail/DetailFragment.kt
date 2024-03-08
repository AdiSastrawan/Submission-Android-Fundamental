package com.adisastrawan.mysearchsubmission.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.adisastrawan.mysearchsubmission.data.respond.DetailUserResponse
import com.adisastrawan.mysearchsubmission.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator


class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = DetailFragmentArgs.fromBundle(
            arguments as Bundle
        ).username
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]
        val sectionsPagerAdapter = SectionsPagerAdapter(activity as AppCompatActivity)
        sectionsPagerAdapter.username = username
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs,binding.viewPager){ tab,position ->
            if(position == 0){
                tab.text = "Follower"
            }else{
                tab.text = "Following"
            }
        }.attach()
        viewModel.getDetailUser(username)
        viewModel.userDetail.observe(this.viewLifecycleOwner){
            setDetailUser(it)
        }
        viewModel.isLoading.observe(this.viewLifecycleOwner){
            showLoading(it)
        }
        viewModel.snackBarText.observe(this.viewLifecycleOwner){ it->
            it.getContentIfNotHandled()?.let {
                Snackbar.make(  (activity as AppCompatActivity).window.decorView.rootView,
                    it,
                    Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun setDetailUser(userDetail:DetailUserResponse) {
        with(binding){
            tvUsername.text = userDetail.login
            tvName.text = userDetail.name
            tvFollower.text = if(userDetail.followers > 1) "${userDetail.followers} followers" else "${userDetail.followers} follower"
            tvFollowing.text = "${userDetail.following} following"
        }
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