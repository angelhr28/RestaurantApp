package com.example.restaurantapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.restaurantapp.data.database.dao.*
import com.example.restaurantapp.data.database.entities.BoardEntity
import com.example.restaurantapp.data.database.entities.CategoryEntity
import com.example.restaurantapp.data.database.entities.ProductEntity
import com.example.restaurantapp.data.database.entities.RequestEntity

@Database(entities = [
    BoardEntity::class,
    CategoryEntity::class,
    ProductEntity::class,
    RequestEntity::class,
    RequestDetailDao::class,
], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun boardDao(): BoardDao
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun requestDao(): RequestDao
    abstract fun requestDetailDao(): RequestDetailDao
}