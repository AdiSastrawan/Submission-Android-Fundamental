package com.adisastrawan.mysearchsubmission.data.remote.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.adisastrawan.mysearchsubmission.BuildConfig

class ApiConfig {
    companion object{
        fun getApiService(): ApiService {
            val authInterceptor = Interceptor{
                val req = it.request()
                val requestHeader = req.newBuilder()
                    .addHeader("Authorization","token ${BuildConfig.API_KEY}")
                    .build()
                it.proceed(requestHeader)
            }
            val loggingInterceptor = if(BuildConfig.DEBUG) { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }else { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE) }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}