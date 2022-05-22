package com.example.restaurantapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurantapp.data.database.entities.RequestDetailEntity

@Dao
interface RequestDetailDao {

    @Query("SELECT * FROM request_details ORDER BY id")
    suspend fun getAll(): List<RequestDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(requestDetails: List<RequestDetailEntity>)

    @Query("DELETE FROM request_details")
    suspend fun deleteAll()
}