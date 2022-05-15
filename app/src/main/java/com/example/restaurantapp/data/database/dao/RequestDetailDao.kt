package com.example.restaurantapp.data.database.dao

import androidx.room.*
import com.example.restaurantapp.data.database.entities.RequestDetailEntity

@Dao
interface RequestDetailDao {

    @Query("SELECT * FROM request_detail_table ORDER BY id")
    suspend fun getAll(): List<RequestDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(requestDetails: List<RequestDetailEntity>)

    @Update(entity = RequestDetailEntity::class)
    suspend fun update(requestDetail: RequestDetailEntity)

    @Delete(entity = RequestDetailEntity::class)
    suspend fun delete(requestDetail: RequestDetailEntity)

    @Query("DELETE FROM request_detail_table")
    suspend fun clearAll()
}