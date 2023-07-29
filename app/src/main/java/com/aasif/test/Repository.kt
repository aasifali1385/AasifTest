package com.aasif.test

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.asLiveData
import com.aasif.test.data.FoodsCate
import com.aasif.test.data.Login
import com.aasif.test.data.Signup
import com.aasif.test.retrofit.ApiInterface
import com.aasif.test.roomDB.Dao
import com.aasif.test.roomDB.Product
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow

class Repository(
    private val sp: SharedPreferences,
    private val rest: ApiInterface,
    private val dao: Dao
) {


    //// Room DB ////
    suspend fun testRoom(): Flow<List<Product>> {
//        dao.addProduct(Product(0, "https://image.com", "First", 111, 10))

        return dao.getProducts()
    }


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

