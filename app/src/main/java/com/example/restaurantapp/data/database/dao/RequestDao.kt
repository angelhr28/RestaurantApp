package com.example.restaurantapp.data.database.dao

import androidx.room.*
import com.example.restaurantapp.data.database.entities.RequestEntity

@Dao
interface RequestDao {

    @Query("SELECT * FROM request_table ORDER BY id")
    suspend fun getAll(): List<RequestEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(requests: List<RequestEntity>)

    @Update(entity = RequestEntity::class)
    suspend fun update(request: RequestEntity)

    @Delete(entity = RequestEntity::class)
    suspend fun delete(request: RequestEntity)

    @Query("DELETE FROM request_table")
    suspend fun clearAll()
}