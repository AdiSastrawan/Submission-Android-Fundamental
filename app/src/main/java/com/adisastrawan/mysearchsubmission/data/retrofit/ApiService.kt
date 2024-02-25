package com.adisastrawan.mysearchsubmission.data.retrofit

import com.adisastrawan.mysearchsubmission.data.respond.GithubUsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getSearchUsers(
        @Query("q") q : String
    ) : Call<GithubUsersResponse>
//    @FormUrlEncoded
//    @Headers("Authorization: token 12345")
}