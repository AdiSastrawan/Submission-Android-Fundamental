package com.adisastrawan.mysearchsubmission.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adisastrawan.mysearchsubmission.data.respond.GithubUsersResponse
import com.adisastrawan.mysearchsubmission.data.respond.ItemsItem
import com.adisastrawan.mysearchsubmission.data.retrofit.ApiConfig
import com.adisastrawan.mysearchsubmission.ui.detail.DetailViewModel
import com.adisastrawan.mysearchsubmission.utils.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    companion object{
        const val TAG = "HomeFragment"
    }
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private var _users = MutableLiveData<List<ItemsItem>>()
    val users : LiveData<List<ItemsItem>> = _users

    private var _snackBarText = MutableLiveData<Event<String>>()
    val snackBarText : LiveData<Event<String>> = _snackBarText
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
                }else{
                    _snackBarText.value = Event(response.message())
                    Log.d(TAG,"onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubUsersResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG,"onFailure : ${t.message}")
            }

        })

    }
}