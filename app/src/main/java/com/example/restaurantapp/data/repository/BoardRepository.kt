package com.example.restaurantapp.data.repository

import com.example.restaurantapp.data.database.dao.BoardDao
import com.example.restaurantapp.data.database.entities.BoardEntity
import com.example.restaurantapp.data.model.BoardModel
import com.example.restaurantapp.data.network.BoardService
import com.example.restaurantapp.domain.mapper.toBoard
import com.example.restaurantapp.domain.mapper.toBoardServices
import com.example.restaurantapp.domain.model.Board
import javax.inject.Inject

class BoardRepository @Inject constructor(
    private val api: BoardService,
    private val boardDao: BoardDao,
) {

    suspend fun getAllBoardsFromApi(): List<Board?> {
        val response = api.getBoards()
        return response.map { it?.toBoard() }
    }

    suspend fun getAllBoardsFromDatabase(): List<Board> {
        val response = boardDao.getAll()
        return response.map { it.toBoardServices() }
    }

    suspend fun insertAllFromApi(boards: List<BoardModel>): List<BoardModel> {
        return api.insertAll(boards)
    }

    suspend fun deleteBoardFromApi(id: Int) {
        api.delete(id)
    }

    suspend fun insertAllFromDataBase(boards: List<BoardEntity>) {
        boardDao.insertAll(boards)
    }

    suspend fun deleteAllFromDataBase() {
        boardDao.deleteAll()
    }

    suspend fun deleteBoardFromDataBase(id: Int) {
        boardDao.delete(id)
    }
}