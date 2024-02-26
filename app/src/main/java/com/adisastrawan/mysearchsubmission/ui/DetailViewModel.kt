package com.adisastrawan.mysearchsubmission.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adisastrawan.mysearchsubmission.data.respond.DetailUserResponse

class DetailViewModel : ViewModel() {
    private var _userDetail  = MutableLiveData<DetailUserResponse>()
    val userDetail : LiveData<DetailUserResponse> = _userDetail

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading


}