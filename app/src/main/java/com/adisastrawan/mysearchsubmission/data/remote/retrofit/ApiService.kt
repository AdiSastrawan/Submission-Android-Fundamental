package com.adisastrawan.mysearchsubmission.data.remote.retrofit

import com.adisastrawan.mysearchsubmission.data.remote.respond.DetailUserResponse
import com.adisastrawan.mysearchsubmission.data.remote.respond.GithubUsersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun getSearchUsers(
        @Query("q") q : String
    ) : GithubUsersResponse
    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username") username : String
    ) : DetailUserResponse
    @GET("users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") username: String
    ): List<DetailUserResponse>
    @GET("users/{username}/following")
    suspend fun getFollowing(
        @Path("username") username: String
    ): List<DetailUserResponse>
}