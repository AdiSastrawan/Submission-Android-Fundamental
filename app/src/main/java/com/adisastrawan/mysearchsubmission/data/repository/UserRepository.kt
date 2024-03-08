package com.adisastrawan.mysearchsubmission.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserDetailEntity
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserEntity
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserFollowerEntity
import com.adisastrawan.mysearchsubmission.data.local.database.room.UserDao
import com.adisastrawan.mysearchsubmission.data.local.database.room.UserDetailDao
import com.adisastrawan.mysearchsubmission.data.local.database.room.UserFollowerDao
import com.adisastrawan.mysearchsubmission.data.remote.retrofit.ApiService

class UserRepository(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val userDetailDao: UserDetailDao,
    private val userFollowerDao: UserFollowerDao
) {
    fun getUsers(username:String) : LiveData<Result<List<UserEntity>>> = liveData{
        emit(Result.Loading)
        try {
            val response =apiService.getSearchUsers(username)
            val users = response.items
            val usersList = users.map {
                UserEntity(it.login,it.avatarUrl)
            }
            userDao.deleteAll()
            userDao.insert(usersList)
        }catch (e: Exception) {
            Log.d(TAG, "getUsers : ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<UserEntity>>> = userDao.getAllUser().map { Result.Success(it) }
        emitSource(localData)
    }
    fun getUserDetail(username: String) : LiveData<Result<UserDetailEntity>> = liveData{
        emit(Result.Loading)
        try {
            val respoonse = apiService.getDetailUser(username)
            val isFavorited = userDetailDao.isUserFavorited(username)
            val userDetail = UserDetailEntity(respoonse.login,respoonse.avatarUrl,respoonse.name,respoonse.followers,respoonse.following,isFavorited)
            userDetailDao.deleteAll()
            userDetailDao.insert(userDetail)
        }catch (e : Exception){
            Log.d(TAG, "getUserDetail : ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
        val localData : LiveData<Result<UserDetailEntity>> = userDetailDao.getUserDetail(username).map { Result.Success(it) }
        emitSource(localData)
    }

    fun getUserFollowers(username: String) : LiveData<Result<List<UserFollowerEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getFollowers(username)
            val usersList = response.map {
                UserFollowerEntity(it.login,it.avatarUrl)
            }
            userFollowerDao.deleteAll()
            userFollowerDao.insert(usersList)
        }catch (e : Exception){
            Log.d(TAG, "getUserFollowers : ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
        val localData : LiveData<Result<List<UserFollowerEntity>>> = userFollowerDao.getAllFollower().map { Result.Success(it)  }
        emitSource(localData)
    }
    fun getUserFollowing(username: String) : LiveData<Result<List<UserFollowerEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getFollowing(username)
            val usersList = response.map {
                UserFollowerEntity(it.login,it.avatarUrl)
            }
            userFollowerDao.deleteAll()
            userFollowerDao.insert(usersList)
        }catch (e : Exception){
            Log.d(TAG, "getUserFollowers : ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
        val localData : LiveData<Result<List<UserFollowerEntity>>> = userFollowerDao.getAllFollower().map { Result.Success(it)  }
        emitSource(localData)
    }
    companion object{
        private const val TAG = "UserRepository"

        @Volatile
        private var instance : UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userDao: UserDao,
            userDetailDao: UserDetailDao,
            userFollowerDao: UserFollowerDao
        ):UserRepository{
            return instance ?: synchronized(this){
                instance ?: UserRepository(apiService,userDao,userDetailDao,userFollowerDao )
            }.also { instance = it }
        }
    }
}