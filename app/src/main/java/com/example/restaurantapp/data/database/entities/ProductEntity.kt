package com.example.restaurantapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.restaurantapp.utils.enums.Status

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "category_id") val categoryId: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "stock") val stock: Int,
    @ColumnInfo(name = "flg_prom") val flgProm: Status,
    @ColumnInfo(name = "desc_prom") val descProm: String,
    @ColumnInfo(name = "status") val status: Status,
)

//fun Request.toDatabase() = RequestEntity(status = status)