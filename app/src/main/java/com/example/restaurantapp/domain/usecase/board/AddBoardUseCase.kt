package com.example.restaurantapp.domain.usecase.board

import com.example.restaurantapp.data.repository.BoardRepository
import com.example.restaurantapp.domain.mapper.toBoardEntity
import com.example.restaurantapp.domain.mapper.toBoardModel
import com.example.restaurantapp.domain.model.Board
import javax.inject.Inject

class AddBoardUseCase @Inject constructor(
    private val repository: BoardRepository,
) {
    suspend operator fun invoke(board: Board) {
        val responseApi = repository.insertAllFromApi(listOf(board.toBoardModel()))
        repository.insertAllFromDataBase(responseApi.map { it.toBoardEntity() })
    }
}