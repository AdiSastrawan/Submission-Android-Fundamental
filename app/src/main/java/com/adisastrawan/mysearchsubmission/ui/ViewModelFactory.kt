package com.adisastrawan.mysearchsubmission.ui

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adisastrawan.mysearchsubmission.data.repository.UserRepository
import com.adisastrawan.mysearchsubmission.di.injection
import com.adisastrawan.mysearchsubmission.ui.detail.DetailViewModel
import com.adisastrawan.mysearchsubmission.ui.detail.follower.FollowerViewModel
import com.adisastrawan.mysearchsubmission.ui.home.MainViewModel

class ViewModelFactory private constructor(private val userRepository: UserRepository) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(userRepository) as T
        }else if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(userRepository) as T
        }else if(modelClass.isAssignableFrom(FollowerViewModel::class.java)){
            return FollowerViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object{
        @Volatile
        private var instance : ViewModelFactory? = null
        fun getInstance(context: Context) : ViewModelFactory{
            return instance ?: synchronized(this){
                instance ?: ViewModelFactory(injection.provideRepository(context))
            }.also { instance = it }
        }
    }
}