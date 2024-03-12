package com.adisastrawan.mysearchsubmission.ui.detail.follower

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adisastrawan.mysearchsubmission.data.repository.UserRepository
import com.adisastrawan.mysearchsubmission.utils.Event

class FollowerViewModel(private val userRepository: UserRepository): ViewModel() {

    private var _snackBarText = MutableLiveData<Event<String>>()
    val snackBarText : LiveData<Event<String>> = _snackBarText
    fun getUserFollowers(username: String) = userRepository.getUserFollowers(username)
    fun getUserFollowing(username: String )= userRepository.getUserFollowing(username)

}