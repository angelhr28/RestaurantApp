package com.example.restaurantapp.domain.usecase.board

import android.util.Log
import com.example.restaurantapp.data.repository.BoardRepository
import javax.inject.Inject

class DeleteBoardUseCase @Inject constructor(
    private val repository: BoardRepository,
) {
    suspend operator fun invoke(id: String) {
        Log.e("TAG", "DeleteBoardUseCase: $id ")
        repository.deleteBoardFromDataBase(id = id)
        repository.deleteBoardFromApi(id = id)
    }
}