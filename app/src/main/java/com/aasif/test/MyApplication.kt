package com.aasif.test

import android.app.Application
import android.content.Context
import com.aasif.test.retrofit.API
import com.aasif.test.roomDB.AppDatabase

class MyApplication : Application() {


    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()

        repository = Repository(
            getSharedPreferences("user", Context.MODE_PRIVATE),
            API.getApiService(),
            API.getCodeOnService()
        )
    }
}