package com.example.restaurantapp.data.database.dao

import androidx.room.*
import com.example.restaurantapp.data.database.entities.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category_table ORDER BY id")
    suspend fun getAll(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<CategoryEntity>)

    @Update(entity = CategoryEntity::class)
    suspend fun update(category: CategoryEntity)

    @Delete(entity = CategoryEntity::class)
    suspend fun delete(category: CategoryEntity)

    @Query("DELETE FROM category_table")
    suspend fun clearAll()
}