package com.adisastrawan.mysearchsubmission.data.local.database.enitity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "follower")
data class UserFollowerEntity(
    @PrimaryKey
    @ColumnInfo(name="username")
    var username : String,

    @ColumnInfo(name="avatarUrl")
    var avatarUrl : String? = null,

)
