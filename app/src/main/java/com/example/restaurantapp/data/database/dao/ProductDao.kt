package com.example.restaurantapp.data.database.dao

import androidx.room.*
import com.example.restaurantapp.data.database.entities.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table ORDER BY id")
    suspend fun getAll(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Update(entity = ProductEntity::class)
    suspend fun update(product: ProductEntity)

    @Delete(entity = ProductEntity::class)
    suspend fun delete(product: ProductEntity)

    @Query("DELETE FROM product_table")
    suspend fun clearAll()
}