package com.aasif.test.data

data class LoginSignupResponse(

    val success: Boolean,
    val statusCode:Int,
    val message: String,
    val data: UserData

)