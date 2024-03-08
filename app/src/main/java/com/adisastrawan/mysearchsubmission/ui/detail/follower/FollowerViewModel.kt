package com.adisastrawan.mysearchsubmission.ui.detail.follower

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adisastrawan.mysearchsubmission.data.remote.respond.DetailUserResponse
import com.adisastrawan.mysearchsubmission.data.remote.retrofit.ApiConfig
import com.adisastrawan.mysearchsubmission.data.repository.UserRepository
import com.adisastrawan.mysearchsubmission.utils.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel(private val userRepository: UserRepository): ViewModel() {
    companion object{
        const val TAG = "FollowerFragment"
    }


    private var _snackBarText = MutableLiveData<Event<String>>()
    val snackBarText : LiveData<Event<String>> = _snackBarText
    fun getUserFollowers(username: String) = userRepository.getUserFollowers(username)
    fun getUserFollowing(username: String )= userRepository.getUserFollowing(username)

}