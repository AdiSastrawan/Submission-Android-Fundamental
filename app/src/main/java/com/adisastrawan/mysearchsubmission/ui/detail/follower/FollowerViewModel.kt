package com.adisastrawan.mysearchsubmission.ui.detail.follower

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adisastrawan.mysearchsubmission.data.respond.DetailUserResponse
import com.adisastrawan.mysearchsubmission.data.retrofit.ApiConfig
import com.adisastrawan.mysearchsubmission.utils.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel : ViewModel() {
    companion object{
        const val TAG = "FollowerFragment"
    }
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private var _users = MutableLiveData<List<DetailUserResponse>>()
    val users : LiveData<List<DetailUserResponse>> = _users

    private var _snackBarText = MutableLiveData<Event<String>>()
    val snackBarText : LiveData<Event<String>> = _snackBarText
    fun getUserFollowers(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<DetailUserResponse>> {
            override fun onResponse(
                call: Call<List<DetailUserResponse>>,
                response: Response<List<DetailUserResponse>>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    if(response.body() != null){
                        _users.value = response.body()
                    }
                }else{
                    _snackBarText.value = Event(response.message())
                    Log.d(TAG,"onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<DetailUserResponse>>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG,"onFailure : ${t.message}")
            }


        })
    }
    fun getUserFollowing(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<DetailUserResponse>> {
            override fun onResponse(
                call: Call<List<DetailUserResponse>>,
                response: Response<List<DetailUserResponse>>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    if(response.body() != null){
                        _users.value = response.body()
                    }
                }
            }

            override fun onFailure(call: Call<List<DetailUserResponse>>, t: Throwable) {
                _isLoading.value = false
                Log.d("FollowerFragment","onFailure : ${t.message}")
            }


        })
    }
}