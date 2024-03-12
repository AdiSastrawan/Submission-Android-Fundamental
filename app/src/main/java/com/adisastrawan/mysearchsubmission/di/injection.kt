package com.adisastrawan.mysearchsubmission.di

import android.content.Context
import com.adisastrawan.mysearchsubmission.data.local.database.room.UserRoomDatabase
import com.adisastrawan.mysearchsubmission.data.remote.retrofit.ApiConfig
import com.adisastrawan.mysearchsubmission.data.repository.UserRepository

object injection {
    fun provideRepository(context: Context) : UserRepository {
        val apiService = ApiConfig.getApiService()
        val database = UserRoomDatabase.getInstance(context)
        val userDao = database.userDao()
        val userDetailDao = database.userDetailDao()
        val userFollowerDao = database.userFollowerDao()
        return UserRepository.getInstance(apiService,userDao,userDetailDao,userFollowerDao)
    }
}