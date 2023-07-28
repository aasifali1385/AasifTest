package com.aasif.test.roomDB

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.Date

class Converters {

    @TypeConverter
    fun toTimestamp(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(long: Long): Date {
        return long.let { Date(it) }
    }


    @TypeConverter
    fun toJson(items: List<ItemSave>): String {
        return Gson().toJson(items)
    }

    @TypeConverter
    fun toList(itemString: String): List<ItemSave> {
        return Gson().fromJson(itemString, Array<ItemSave>::class.java).toList()
    }

}