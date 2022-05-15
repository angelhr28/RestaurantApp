package com.example.restaurantapp.data.database.dao

import androidx.room.*
import com.example.restaurantapp.data.database.entities.BoardEntity

@Dao
interface BoardDao {

    @Query("SELECT * FROM board_table ORDER BY id")
    suspend fun getAll(): List<BoardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(boards: List<BoardEntity>)

    @Update(entity = BoardEntity::class)
    suspend fun update(board: BoardEntity)

    @Delete(entity = BoardEntity::class)
    suspend fun delete(board: BoardEntity)

    @Query("DELETE FROM board_table")
    suspend fun clearAll()
}