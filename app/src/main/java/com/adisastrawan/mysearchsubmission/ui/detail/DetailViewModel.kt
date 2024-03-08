package com.adisastrawan.mysearchsubmission.ui.detail

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

class DetailViewModel (private val userRepository: UserRepository) : ViewModel() {
    companion object{
        const val TAG ="DetailFragment"
    }
    private var _userDetail  = MutableLiveData<DetailUserResponse>()
    val userDetail : LiveData<DetailUserResponse> = _userDetail

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private var _snackBarText = MutableLiveData<Event<String>>()
    val snackBarText : LiveData<Event<String>> = _snackBarText
    fun getDetailUser(username:String) = userRepository.getUserDetail(username)
}