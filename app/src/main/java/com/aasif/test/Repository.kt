package com.aasif.test

import android.content.SharedPreferences
import android.util.Log
import com.aasif.test.data.Career
import com.aasif.test.data.Login
import com.aasif.test.data.LoginSignupResponse
import com.aasif.test.data.Signup
import com.aasif.test.data.UserData
import com.aasif.test.retrofit.ApiInterface
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class Repository(
    private val sp: SharedPreferences,
    private val rest: ApiInterface,
    private val codeon: ApiInterface
) {

    //// Shared Preferences ////

    fun saveUser(user: UserData) {
        //                val jsonObj = JSONObject(response.toString())
//                val user = jsonObj.getJSONObject("data")
        val editor = sp.edit()
        editor.putString("name", user.name)
        editor.putString("email", user.email)
        editor.putString("phone", user.phone_number)
        editor.apply()
    }

    fun getUser(): String? {
        return sp.getString("name", null)
    }

    ////  REST API  ////

    fun loginUser(login: Login, response: (Response<LoginSignupResponse?>) -> Unit) {
        rest.login(login).enqueue(object : Callback<LoginSignupResponse?> {
            override fun onResponse(
                call: Call<LoginSignupResponse?>,
                response: Response<LoginSignupResponse?>
            ) {
                response(response)
            }

            override fun onFailure(call: Call<LoginSignupResponse?>, t: Throwable) {
            }
        })
    }

    fun registerUser(signup: Signup, response: (Response<LoginSignupResponse?>) -> Unit) {
        rest.register(signup).enqueue(object : Callback<LoginSignupResponse?> {
            override fun onResponse(
                call: Call<LoginSignupResponse?>,
                response: Response<LoginSignupResponse?>
            ) {
                response(response)
            }

            override fun onFailure(call: Call<LoginSignupResponse?>, t: Throwable) {
            }
        })
    }

    suspend fun getCategories() = rest.getCategories()

    suspend fun upload(career: Career, file: File): JsonObject {

        val nameBody = career.name.toRequestBody("text/plain".toMediaTypeOrNull())
        val contactBody = career.contact.toRequestBody("text/plain".toMediaTypeOrNull())
        val emailBody = career.email.toRequestBody("text/plain".toMediaTypeOrNull())
        val applyBody = career.apply.toRequestBody("text/plain".toMediaTypeOrNull())

        val fileBody = file.asRequestBody("application/octet-stream".toMediaTypeOrNull())
        val filePart = MultipartBody.Part.createFormData("cv_doc", file.name, fileBody)

        return codeon.apply(nameBody, contactBody, emailBody, applyBody, filePart)
    }


}

