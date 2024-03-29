package com.adisastrawan.mysearchsubmission.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.adisastrawan.mysearchsubmission.R
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserDetailEntity
import com.adisastrawan.mysearchsubmission.data.repository.Result
import com.adisastrawan.mysearchsubmission.databinding.FragmentDetailBinding
import com.adisastrawan.mysearchsubmission.ui.ViewModelFactory
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator


class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = DetailFragmentArgs.fromBundle(
            arguments as Bundle
        ).username
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel : DetailViewModel by viewModels{factory}
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
        viewModel.getDetailUser(username).observe(viewLifecycleOwner){result->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val user = result.data
                        setDetailUser(user)
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
        viewModel.snackBarText.observe(this.viewLifecycleOwner){ it->
            it.getContentIfNotHandled()?.let {
                Snackbar.make(  (activity as AppCompatActivity).window.decorView.rootView,
                    it,
                    Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.fabFavorite.setOnClickListener{
            val isFavorited = viewModel.isUserFavorited(username)
            viewModel.updateToFavorite(username)
            if(isFavorited){
                binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
            }else{
                binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
            }
        }
        binding.fabShare.setOnClickListener{
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT,"https://github.com/$username")
                type ="text/plain"
            }
            val shareIntent = Intent.createChooser(intent,null)
            startActivity(shareIntent)
        }
    }

    private fun setDetailUser(userDetail: UserDetailEntity) {
        with(binding){
            tvUsername.text = userDetail.username
            tvName.text = userDetail.name
            tvFollower.text = if(userDetail.followers > 1) resources.getString(R.string.followers,userDetail.followers) else "${userDetail.followers} follower"
            tvFollowing.text = resources.getString(R.string.following,userDetail.following)
            if(userDetail.isFavorite){
                fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
            }else{
                fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
            }
        }
        Glide.with(this).load(userDetail.avatarUrl).into(binding.ivAvatar)

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }

}