package com.example.restaurantapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.restaurantapp.data.database.dao.RequestDao
import com.example.restaurantapp.data.database.entities.RequestEntity

@Database(entities = [RequestEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun developerDao(): RequestDao
}