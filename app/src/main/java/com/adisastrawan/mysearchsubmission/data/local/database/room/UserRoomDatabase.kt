package com.adisastrawan.mysearchsubmission.data.local.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserDetailEntity
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserEntity
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserFollowerEntity

@Database(entities = [UserEntity::class,UserDetailEntity::class,UserFollowerEntity::class], version = 1, exportSchema = false)
abstract class UserRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userDetailDao() : UserDetailDao
    abstract fun userFollowerDao() : UserFollowerDao
    companion object{
        @Volatile
        private var instance : UserRoomDatabase? = null

        @JvmStatic
        fun getInstance(context : Context) : UserRoomDatabase {
            if(instance == null){
                synchronized(UserRoomDatabase::class.java){
                    instance = Room.databaseBuilder(context, UserRoomDatabase::class.java,"user_database").build()

                }
            }
            return instance as UserRoomDatabase
        }
    }
}