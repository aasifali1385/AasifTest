package com.aasif.test.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(

    @PrimaryKey(autoGenerate = true)
    val pid: Int,
    val image:String,
    val title: String,
    val price: Int,
    val quantity: Int

)
