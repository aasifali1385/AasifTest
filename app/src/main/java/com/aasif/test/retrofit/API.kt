package com.aasif.test.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object API {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://dev.bosselt.com/backend-assessment/public/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private val codeon by lazy {

        Retrofit.Builder().baseUrl("https://api.codeon24.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).client(
                OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build()


            ).build()
    }

    fun getApiService(): ApiInterface = retrofit.create(ApiInterface::class.java)

    fun getCodeOnService(): ApiInterface = codeon.create(ApiInterface::class.java)

}