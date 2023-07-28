package com.aasif.test.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Invoice(

    @PrimaryKey(autoGenerate = true)
    val iid: Int,
    val date: Date,
    val item: List<ItemSave>,
    val total: Int

)
