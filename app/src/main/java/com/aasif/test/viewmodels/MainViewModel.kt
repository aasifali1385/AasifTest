package com.aasif.test.viewmodels

import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aasif.test.Repository
import com.aasif.test.data.Career
import com.aasif.test.data.FoodsCate
import com.aasif.test.data.Login
import com.aasif.test.data.Signup
import com.aasif.test.roomDB.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File

class MainViewModel(private val repository: Repository) : ViewModel() {


    private val _foods = MutableLiveData<List<FoodsCate>>()
    val foods: LiveData<List<FoodsCate>> get() = _foods


    init {

//        viewModelScope.launch {
//            Log.e("test", "Start")
//            val career = Career(
//                "John Doe",
//                "1234567890",
//                "johndoe@example.com",
//                "Applying for a position"
//            )
//            val file = File(Environment.getExternalStorageDirectory(), "aaa.pdf")
//            val res = repository.upload(career, file)
//            Log.e("test", "Response : $res")
//        }

    }


    fun isLoggedIn(): Boolean {
        return repository.getUser() != null
    }


    fun getCategories() {
        viewModelScope.launch {
            _foods.value = repository.getCategories()
        }
    }

    fun login(login: Login, loginSuccess: () -> Unit, loginFailed: (String) -> Unit) {
        viewModelScope.launch {

            val response = repository.loginUser(login)

            if (response == null)
                loginFailed("Something Went Wrong !")
            else {
                loginSuccess()
//                 Save User
//                val jsonObj = JSONObject(response.toString())
//                val user = jsonObj.getJSONObject("data")
                repository.saveUser(
                    mapOf(
                        "name" to response.data.name,
                        "email" to response.data.email,
                        "phone" to response.data.phone_number
                    )
                )
            }
        }
    }

    fun register(signup: Signup, signupSuccess: () -> Unit, signupFailed: (String) -> Unit) {
        viewModelScope.launch {

            val response = repository.registerUser(signup)

            if (response == null)
                signupFailed("Something Went Wrong !")
            else {
                signupSuccess()
                // Save User
//                val jsonObj = JSONObject(response.toString())
//                val user = jsonObj.getJSONObject("data")
                repository.saveUser(
                    mapOf(
                        "name" to response.data.name,
                        "email" to response.data.email,
                        "phone" to response.data.phone_number
                    )
                )
            }

        }
    }


}