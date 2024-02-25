package com.adisastrawan.mysearchsubmission.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.adisastrawan.mysearchsubmission.data.respond.GithubUsersResponse
import com.adisastrawan.mysearchsubmission.data.respond.ItemsItem
import com.adisastrawan.mysearchsubmission.data.retrofit.ApiConfig
import com.adisastrawan.mysearchsubmission.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager
        val itemDecorations = DividerItemDecoration(this, layoutManager.orientation)
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
        viewModel.users.observe(this) {
            setGithubUsers(it)
        }
        viewModel.isLoading.observe(this){
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
}