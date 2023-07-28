package com.aasif.test.roomDB

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.File
import java.util.Calendar
import java.util.Date

@SuppressLint("SimpleDateFormat")
class DBViewModel(application: Application) : AndroidViewModel(application) {

    //    val _items: MutableLiveData<List<Product>> = MutableLiveData()
    lateinit var items: LiveData<List<Product>>
    lateinit var invoices: LiveData<List<Invoice>>

    private var dao: Dao

    init {
        dao = AppDatabase.getDatabase(application).dao()
    }


    fun getSaleSum(today: (Int) -> Unit, last1: (Int) -> Unit, last30: (Int) -> Unit) {
        viewModelScope.launch {
            val cal = Calendar.getInstance()
            cal.time = Date()

            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            val start = cal.time.time

            cal.set(Calendar.HOUR_OF_DAY, 23)
            cal.set(Calendar.MINUTE, 59)
            cal.set(Calendar.SECOND, 59)
            val end = cal.time.time

            cal.add(Calendar.DAY_OF_YEAR, -1)
            val lastEnd = cal.time.time

            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            val start1 = cal.time.time

//            cal.add(Calendar.DAY_OF_YEAR, -6)
//            val start7 = cal.time.time

            cal.add(Calendar.DAY_OF_YEAR, -29)
            val start30 = cal.time.time

            dao.getTodaySale(start, end)?.let { today(it) }
            dao.getTodaySale(start1, lastEnd)?.let { last1(it) }
//            dao.getTodaySale(start7, lastEnd)?.let { last7(it) }
            dao.getTodaySale(start30, lastEnd)?.let { last30(it) }
        }
    }


    fun getInvoices() {
        invoices = dao.getInvoices().asLiveData()
//        viewModelScope.launch {
//            out(dao.getInvoices().reversed())
//        }
    }

    fun addInvoice(invoice: Invoice, out: (Long) -> Unit) {
        viewModelScope.launch {
            out(dao.addInvoice(invoice))
        }
    }

    fun deleteInvoice(invoice: Invoice) {
        viewModelScope.launch {
            dao.deleteInvoice(invoice)
        }
    }


    fun getProduct() {
        items = dao.getProducts().asLiveData()
    }

    fun deleteProduct(product: Product) {
        File(product.image).delete()
        viewModelScope.launch { dao.deleteProduct(product) }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch { dao.updateProduct(product) }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch { dao.addProduct(product) }
    }


    fun updateQty(pid: Int, inc: Int) {
        viewModelScope.launch { dao.updateQty(pid, inc) }
    }


}