package com.example.restaurantapp.domain.usecase.board

import com.example.restaurantapp.data.repository.BoardRepository
import com.example.restaurantapp.domain.model.Board
import com.example.restaurantapp.utils.enums.Status
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetBoardUseCaseTest {

    @RelaxedMockK
    private lateinit var boardRepository: BoardRepository

    private lateinit var getBoardUseCase: GetBoardUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getBoardUseCase = GetBoardUseCase(boardRepository)
    }

    @Test
    fun main() {
        `validate that if there is already a board it shows them`()
        `validate that it requests data from the api when there are no boards`()
    }

    @Test
    fun `validate that it requests data from the api when there are no boards`() = runBlocking {
        //Given
        coEvery { boardRepository.getAllBoardsFromDatabase() } returns emptyList()
        //When
        getBoardUseCase()
        //Then
        coVerify(exactly = 1) { boardRepository.getAllBoardsFromApi() }
        coVerify(exactly = 1) { boardRepository.insertAllFromDataBase(any()) }
    }

    @Test
    fun `validate that if there is already a board it shows them`() = runBlocking {
        //Given
        val board = listOf(Board("1", Status.ENABLE))
        coEvery { boardRepository.getAllBoardsFromDatabase() } returns board
        //When
        val response = getBoardUseCase()
        //Then
        coVerify(exactly = 0) { boardRepository.getAllBoardsFromApi() }
        coVerify(exactly = 0) { boardRepository.insertAllFromDataBase(any()) }
        assert(response == board)
    }
}