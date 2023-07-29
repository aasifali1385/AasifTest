package com.aasif.test.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("SELECT * FROM product")
    fun getProducts(): Flow<List<Product>>

    @Insert
    suspend fun addProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("UPDATE product SET quantity = quantity + :inc WHERE pid=:pid")
    suspend fun updateQty(pid: Int, inc: Int)

    //---------------- Invoice --------------------

//    @Query("SELECT * FROM invoice")
//    fun getInvoices(): Flow<List<Invoice>>
//
//    @Insert
//    suspend fun addInvoice(invoice: Invoice): Long
//
//    @Delete
//    suspend fun deleteInvoice(invoice: Invoice)
//
//    @Query("SELECT SUM(total) FROM invoice WHERE date BETWEEN :start AND :end")
//    suspend fun getTodaySale(start: Long, end: Long): Int?


}