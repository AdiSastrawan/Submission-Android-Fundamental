package com.adisastrawan.mysearchsubmission.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adisastrawan.mysearchsubmission.data.repository.UserRepository
import com.adisastrawan.mysearchsubmission.utils.Event

class DetailViewModel (private val userRepository: UserRepository) : ViewModel() {
    private var _snackBarText = MutableLiveData<Event<String>>()
    val snackBarText : LiveData<Event<String>> = _snackBarText
    fun getDetailUser(username:String) = userRepository.getUserDetail(username)
    fun updateToFavorite(username: String) = userRepository.updateToFavorite(username)
    fun isUserFavorited(username: String) = userRepository.isUserFavorited(username)
}