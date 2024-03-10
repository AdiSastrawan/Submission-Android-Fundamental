package com.adisastrawan.mysearchsubmission.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adisastrawan.mysearchsubmission.data.repository.UserRepository
import com.adisastrawan.mysearchsubmission.di.injection
import com.adisastrawan.mysearchsubmission.ui.detail.DetailViewModel
import com.adisastrawan.mysearchsubmission.ui.detail.follower.FollowerViewModel
import com.adisastrawan.mysearchsubmission.ui.favorite.FavoriteViewModel
import com.adisastrawan.mysearchsubmission.ui.home.MainViewModel
import com.adisastrawan.mysearchsubmission.ui.setting.SettingPreferences
import com.adisastrawan.mysearchsubmission.ui.setting.dataStore

class ViewModelFactory private constructor(private val userRepository: UserRepository,private val preferences: SettingPreferences) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(userRepository) as T
        }else if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(userRepository) as T
        }else if(modelClass.isAssignableFrom(FollowerViewModel::class.java)){
            return FollowerViewModel(userRepository) as T
        }else if(modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
            return FavoriteViewModel(userRepository) as T
        }else if(modelClass.isAssignableFrom(SettingViewModel::class.java)){
            return SettingViewModel(preferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object{
        @Volatile
        private var instance : ViewModelFactory? = null
        fun getInstance(context: Context) : ViewModelFactory{
            return instance ?: synchronized(this){
                instance ?: ViewModelFactory(injection.provideRepository(context),
                    SettingPreferences.getInstance(context.dataStore))
            }.also { instance = it }
        }
    }
}