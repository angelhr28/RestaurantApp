package com.example.restaurantapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.restaurantapp.utils.enums.DeliveryType
import com.example.restaurantapp.utils.enums.PaymentMethod
import com.example.restaurantapp.utils.enums.RequestStatus
import com.example.restaurantapp.utils.enums.Status

@Entity(tableName = "requests")
data class RequestEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "board_id") val boardId: Int,
    @ColumnInfo(name = "request_status") val requestStatus: RequestStatus,
    @ColumnInfo(name = "total_amount") val totalAmount: Double,
    @ColumnInfo(name = "total_quantity") val totalQuantity: Int,
    @ColumnInfo(name = "delivery_type") val deliveryType: DeliveryType,
    @ColumnInfo(name = "delivery_address") val deliveryAddress: String?,
    @ColumnInfo(name = "references") val references: String?,
    @ColumnInfo(name = "payment_method") val paymentMethod: PaymentMethod?,
    @ColumnInfo(name = "paid_date") val paidDate: String,
    @ColumnInfo(name = "create_at") val createAt: String,
    @ColumnInfo(name = "status") val status: Status,
)