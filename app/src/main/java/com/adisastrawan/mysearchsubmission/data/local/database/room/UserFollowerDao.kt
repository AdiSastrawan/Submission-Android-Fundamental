package com.adisastrawan.mysearchsubmission.data.local.database.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserFollowerEntity

@Dao
interface UserFollowerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(followersEntity: List<UserFollowerEntity>)

    @Query("DELETE FROM follower")
    suspend fun deleteAll()

    @Query("SELECT * FROM follower")
    fun getAllFollower() : LiveData<List<UserFollowerEntity>>

}