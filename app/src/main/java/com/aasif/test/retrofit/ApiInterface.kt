package com.aasif.test.retrofit

import com.aasif.test.data.FoodsCate
import com.aasif.test.data.Login
import com.aasif.test.data.Signup
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {


    @POST("api/login")
    suspend fun login(@Body login: Login): JsonObject

    @POST("api/register")
    suspend fun register(@Body signup: Signup): JsonObject


    @GET("categories")
    suspend fun getCategories(): List<FoodsCate>


}