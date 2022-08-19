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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class AddBoardUseCaseTest {

    @RelaxedMockK
    private lateinit var boardRepository: BoardRepository

    private lateinit var addBoardUseCase: AddBoardUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        addBoardUseCase = AddBoardUseCase(boardRepository)
    }

    @Test
    fun main() {
        `insert a boards to the database`()
    }

    @Test
    fun `insert a boards to the database`() = runBlocking {
        //Given
        val boards = listOf(BoardModel("1", Status.ENABLE))
        coEvery { boardRepository.insertAllFromApi(any()) } returns boards
        //When
        val response = addBoardUseCase(boards.first().toBoard())
        //Then
        coVerify(exactly = 1) { boardRepository.insertAllFromDataBase(any()) }

        //Al ser un nuevo registro este debe tener como respuesta un valor diferente a nulo
        assertNotEquals(response, null)
        // El valor de la respuesta debe ser igual al id que se coloca inicialmente
        assertEquals(response, "1")
    }
}