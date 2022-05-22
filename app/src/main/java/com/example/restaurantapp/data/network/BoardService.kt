package com.example.restaurantapp.data.network

import com.example.restaurantapp.data.model.BoardModel
import com.example.restaurantapp.domain.mapper.toBoardServices
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BoardService @Inject constructor(
    private val firestore: FirebaseFirestore,
) {

    suspend fun getBoards(): List<BoardModel?> {
        return firestore.collection("boards").get().await().documents.map { it.toBoardServices() }
    }

    suspend fun delete(id: Int) {
        firestore.collection("board").document(id.toString())
            .delete().await()
    }

    suspend fun insertAll(boards: List<BoardModel>): List<BoardModel> {
        boards.map {
            val a = firestore.collection("boards")
                .add(it.toBoardServices()).await()
            it.id = a.id
            it
        }
        return boards
    }
}