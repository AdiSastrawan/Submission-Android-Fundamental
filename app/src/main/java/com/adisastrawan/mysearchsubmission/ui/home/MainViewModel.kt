package com.adisastrawan.mysearchsubmission.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adisastrawan.mysearchsubmission.data.repository.UserRepository
import com.adisastrawan.mysearchsubmission.utils.Event

class MainViewModel(private val userRepository: UserRepository): ViewModel() {
    private var _snackBarText = MutableLiveData<Event<String>>()
    val snackBarText : LiveData<Event<String>> = _snackBarText
    fun getGithubUsers(query:String) = userRepository.getUsers(query)
}