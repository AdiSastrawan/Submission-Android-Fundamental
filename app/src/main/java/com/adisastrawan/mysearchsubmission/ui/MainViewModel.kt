package com.adisastrawan.mysearchsubmission.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adisastrawan.mysearchsubmission.data.respond.GithubUsersResponse
import com.adisastrawan.mysearchsubmission.data.respond.ItemsItem
import com.adisastrawan.mysearchsubmission.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private var _users = MutableLiveData<List<ItemsItem>>()
    val users : LiveData<List<ItemsItem>> = _users
    init{
        getGithubUsers()
    }
    fun getGithubUsers(query:String = "Adi") {
        _isLoading.value =true
        val client = ApiConfig.getApiService().getSearchUsers(query)
        client.enqueue(object: Callback<GithubUsersResponse> {
            override fun onResponse(
                call: Call<GithubUsersResponse>,
                response: Response<GithubUsersResponse>
            ) {
                _isLoading.value =false
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        _users.value = responseBody.items
                    }
                }
            }

            override fun onFailure(call: Call<GithubUsersResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d("MainActivity","onFailure : ${t.message}")
            }

        })

    }
}