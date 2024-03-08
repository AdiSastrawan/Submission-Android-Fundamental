package com.adisastrawan.mysearchsubmission.data.local.database.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserDetailEntity

@Dao
interface UserDetailDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userDetailEntity: UserDetailEntity)

    @Update
    suspend fun updateUser(userDetailEntity: UserDetailEntity)
    @Query("SELECT EXISTS(SELECT * FROM user_detail WHERE username = :username AND favorited = 1)")
    suspend fun isUserFavorited(username: String): Boolean

    @Query("SELECT * FROM user_detail WHERE username = :username")
    fun getUserDetail(username: String) : LiveData<UserDetailEntity>
    @Query("DELETE FROM user_detail WHERE favorited = 0 ")
    suspend fun deleteAll()
    @Query("SELECT * FROM user_detail where favorited = 1")
    fun getFavoriteUsers(): LiveData<List<UserDetailEntity>>
}