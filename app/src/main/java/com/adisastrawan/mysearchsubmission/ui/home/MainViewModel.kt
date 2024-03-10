package com.adisastrawan.mysearchsubmission.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adisastrawan.mysearchsubmission.data.remote.respond.GithubUsersResponse
import com.adisastrawan.mysearchsubmission.data.remote.respond.ItemsItem
import com.adisastrawan.mysearchsubmission.data.remote.retrofit.ApiConfig
import com.adisastrawan.mysearchsubmission.data.repository.UserRepository
import com.adisastrawan.mysearchsubmission.ui.detail.DetailViewModel
import com.adisastrawan.mysearchsubmission.utils.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val userRepository: UserRepository): ViewModel() {
    private var _snackBarText = MutableLiveData<Event<String>>()
    val snackBarText : LiveData<Event<String>> = _snackBarText
    fun getGithubUsers(query:String) = userRepository.getUsers(query)
}