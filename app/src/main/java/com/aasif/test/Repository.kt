package com.aasif.test

import android.content.SharedPreferences
import com.aasif.test.data.FoodsCate
import com.aasif.test.data.Login
import com.aasif.test.data.Signup
import com.aasif.test.retrofit.ApiInterface
import com.google.gson.JsonObject

class Repository(private val sp: SharedPreferences, private val rest: ApiInterface) {


    //// Shared Preferences ////

    fun saveUser(user: Map<String, String>) {
        val editor = sp.edit()
        editor.putString("name", user["name"])
        editor.putString("email", user["email"])
        editor.putString("phone", user["phone"])
        editor.apply()
    }

    fun getUser(): String? {
        return sp.getString("name", null)
    }

    ////  REST API  ////

    suspend fun loginUser(login: Login): JsonObject? {
        return rest.login(login)
    }

    suspend fun registerUser(signup: Signup): JsonObject? {
        return rest.register(signup)
    }

    suspend fun getCategories(): List<FoodsCate> {
        return rest.getCategories()
    }

}

