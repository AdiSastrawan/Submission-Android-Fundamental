package com.adisastrawan.mysearchsubmission.data.local.database.enitity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user")
@Parcelize
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name="username")
    var username : String,
    @ColumnInfo(name="avatarUrl")
    var avatarUrl : String? = null,
) : Parcelable
