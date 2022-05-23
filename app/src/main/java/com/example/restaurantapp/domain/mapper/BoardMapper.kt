package com.example.restaurantapp.domain.mapper

import android.util.Log
import com.example.restaurantapp.data.database.entities.BoardEntity
import com.example.restaurantapp.data.model.BoardModel
import com.example.restaurantapp.domain.model.Board
import com.example.restaurantapp.utils.enums.Status
import com.example.restaurantapp.utils.parseDate
import com.example.restaurantapp.utils.toDate
import com.google.firebase.firestore.DocumentSnapshot

fun DocumentSnapshot.toBoardServices(): BoardModel? {
    return try {
        var obj: BoardModel? = null
        data?.apply {
            val status = ("" + (get("status") ?: "0"))
            val createdAt = (get("created_at") ?: "") as String
            val updatedAt = (get("updated_at") ?: "") as String

            obj = BoardModel(
                id = id,
                status = if (Status.ENABLE.code == status.toInt()) Status.ENABLE else Status.DISABLE,
                createdAt = createdAt.toDate(),
                updatedAt = updatedAt.toDate()
            )
        }
        return obj
    } catch (e: Exception) {
        Log.e("TAG", "Error toBoardServices: $e")
        null
    }
}

fun BoardModel.toBoardServices(): HashMap<String, Any> {
    return hashMapOf(
        "status" to status.code,
        "created_at" to createdAt.toString().parseDate(),
        "updated_at" to updatedAt.toString().parseDate(),
    )
}

fun BoardEntity.toBoardServices() = Board(id, if (Status.ENABLE == status)
    Status.ENABLE else Status.DISABLE, createdAt, updatedAt)

fun BoardModel.toBoard() = Board(id, status, createdAt, updatedAt)
fun BoardModel.toBoardEntity() = BoardEntity(id, status)

fun Board.toBoardModel() = BoardModel(id, status)

fun Board.toBoardEntity() = BoardEntity(id, status)