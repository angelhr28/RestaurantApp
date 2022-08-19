package com.example.restaurantapp.domain.usecase.board

import com.example.restaurantapp.data.model.BoardModel
import com.example.restaurantapp.data.repository.BoardRepository
import com.example.restaurantapp.domain.mapper.toBoard
import com.example.restaurantapp.utils.enums.Status
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DeleteBoardUseCaseTest {

    @RelaxedMockK
    private lateinit var boardRepository: BoardRepository

    private lateinit var deleteBoardUseCase: DeleteBoardUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        deleteBoardUseCase = DeleteBoardUseCase(boardRepository)
    }

    @Test
    fun main() {
        `delete a boards to the database`()
    }

    @Test
    fun `delete a boards to the database`() = runBlocking {
        //Given
        //When
        deleteBoardUseCase("1")
        //Then
        coVerify(exactly = 1) { boardRepository.deleteBoardFromDataBase(any()) }
        coVerify(exactly = 1) { boardRepository.deleteBoardFromApi(any()) }
    }
}