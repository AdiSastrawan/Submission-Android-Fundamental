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

    }



}