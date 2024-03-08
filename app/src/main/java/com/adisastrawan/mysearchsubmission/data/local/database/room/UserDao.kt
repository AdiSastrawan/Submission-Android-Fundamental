package com.adisastrawan.mysearchsubmission.data.local.database.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userEntity: List<UserEntity>)

    @Query("DELETE FROM user ")
    suspend fun deleteAll()

    @Query("SELECT * FROM user ")
    fun getAllUser() : LiveData<List<UserEntity>>

}