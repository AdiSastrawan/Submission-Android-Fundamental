package com.adisastrawan.mysearchsubmission.ui.favorite

import androidx.lifecycle.ViewModel
import com.adisastrawan.mysearchsubmission.data.repository.UserRepository


class FavoriteViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getFavoritedUsers() = userRepository.getFavoritedUsers()
}