package com.example.restaurantapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurantapp.data.database.entities.RequestEntity

@Dao
interface RequestDao {

    @Query("SELECT * FROM request_table ORDER BY author DESC")
    suspend fun getAllDeveloper(): List<RequestEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(developers: List<RequestEntity>)

    @Query("DELETE FROM request_table")
    suspend fun clearAll()
}