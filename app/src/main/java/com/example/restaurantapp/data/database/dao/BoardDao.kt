package com.example.restaurantapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurantapp.data.database.entities.BoardEntity

@Dao
interface BoardDao {

    @Query("SELECT * FROM boards")
    suspend fun getAll(): List<BoardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(boards: List<BoardEntity>)

    @Query("DELETE FROM boards WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM boards")
    suspend fun deleteAll()
}