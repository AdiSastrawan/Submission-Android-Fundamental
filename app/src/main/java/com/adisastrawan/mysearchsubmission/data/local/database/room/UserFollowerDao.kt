package com.adisastrawan.mysearchsubmission.data.local.database.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserFollowerEntity

@Dao
interface UserFollowerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(followersEntity: List<UserFollowerEntity>)

    @Query("DELETE FROM follower where is_follower = 1")
    suspend fun deleteAllFollower()
    @Query("DELETE FROM follower where is_follower = 0")
    suspend fun deleteAllFollowing()

    @Query("SELECT * FROM follower where is_follower = 1")
    fun getAllFollower() : LiveData<List<UserFollowerEntity>>
    @Query("SELECT * FROM follower where is_follower = 0")
    fun getAllFollowing() : LiveData<List<UserFollowerEntity>>

}