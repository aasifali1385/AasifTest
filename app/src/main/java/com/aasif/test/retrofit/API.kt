package com.aasif.test.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://dev.bosselt.com/backend-assessment/public/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getApiService() : ApiInterface = retrofit.create(ApiInterface::class.java)

}