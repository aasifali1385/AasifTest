package com.aasif.test.retrofit

import com.aasif.test.data.FoodsCate
import com.aasif.test.data.Login
import com.aasif.test.data.LoginSignupResponse
import com.aasif.test.data.Signup
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiInterface {


    @POST("api/login")
    suspend fun login(@Body login: Login): LoginSignupResponse?

    @POST("api/register")
    suspend fun register(@Body signup: Signup): LoginSignupResponse?

    @GET("categories")
    suspend fun getCategories(): List<FoodsCate>


    @Multipart
    @POST("Carrer")
    suspend fun apply(
        @Part("name") name: RequestBody,
        @Part("contact") contact: RequestBody,
        @Part("email") email: RequestBody,
        @Part("apply") apply: RequestBody,
        @Part file: MultipartBody.Part
    ): JsonObject


}