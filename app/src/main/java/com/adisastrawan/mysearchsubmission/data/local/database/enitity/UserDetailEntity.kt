package com.adisastrawan.mysearchsubmission.data.local.database.enitity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_detail")
@Parcelize
data class UserDetailEntity(
    @ColumnInfo(name="username")
    @PrimaryKey
    var username : String,

    @ColumnInfo(name="avatarUrl")
    var avatarUrl : String? = null,

    @ColumnInfo(name="name")
    var name : String? = null,

    @ColumnInfo(name="followers")
    var followers : Int = 0,

    @ColumnInfo(name="following")
    var following : Int = 0,

    @ColumnInfo(name="favorited")
    var isFavorite : Boolean =false
) : Parcelable