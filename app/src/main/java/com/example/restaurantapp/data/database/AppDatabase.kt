package com.example.restaurantapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.restaurantapp.data.database.dao.*
import com.example.restaurantapp.data.database.entities.*
import com.example.restaurantapp.utils.Converters

@Database(entities = [
    BoardEntity::class,
    CategoryEntity::class,
    ProductEntity::class,
    RequestEntity::class,
    RequestDetailEntity::class,
], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun boardDao(): BoardDao
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun requestDao(): RequestDao
    abstract fun requestDetailDao(): RequestDetailDao
}