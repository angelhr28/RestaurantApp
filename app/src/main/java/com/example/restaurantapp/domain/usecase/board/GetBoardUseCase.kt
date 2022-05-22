package com.example.restaurantapp.domain.usecase.board

import com.example.restaurantapp.data.repository.BoardRepository
import com.example.restaurantapp.domain.mapper.toBoardEntity
import com.example.restaurantapp.domain.model.Board
import javax.inject.Inject

class GetBoardUseCase @Inject constructor(
    private val repository: BoardRepository,
) {
    suspend operator fun invoke(): List<Board?> {
        var response: List<Board?> = repository.getAllBoardsFromDatabase()
        if (response.isEmpty()) {
            val responseApi = repository.getAllBoardsFromApi()
            repository.insertAllFromDataBase(responseApi.map { it!!.toBoardEntity() })
            response = repository.getAllBoardsFromDatabase()
        }
        return response
    }
}