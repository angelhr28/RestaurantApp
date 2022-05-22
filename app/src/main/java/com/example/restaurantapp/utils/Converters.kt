package com.example.restaurantapp.utils

import androidx.room.TypeConverter
import com.example.restaurantapp.utils.enums.Status
import java.util.*

class Converters {
//    @TypeConverter
//    fun fromString(value: String): ArrayList<String> {
//        val listType = object : TypeToken<ArrayList<String>>() {
//
//        }.type
//        return Gson().fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun fromAnyToString(list: Any): String {
//        return list.toJson()
//    }

    @TypeConverter
    fun toDate(timestamp: Long): Date {
        return Date(timestamp).toString().parseDate().toDate()
    }

    @TypeConverter
    fun toTimestamp(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toStatus(status: Status): Int {
        return status.code
    }
}