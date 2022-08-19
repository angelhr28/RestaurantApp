package com.example.restaurantapp.domain.usecase.user

import com.example.restaurantapp.data.repository.UserRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CheckInUserUseCaseTest{

    @RelaxedMockK
    private lateinit var userRepository: UserRepository

    private lateinit var verifyLogInUseCase: VerifyLogInUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        verifyLogInUseCase = VerifyLogInUseCase(userRepository)
    }

    @Test
    fun main() {
        `check if the user is not logged in`()
    }

    @Test
    fun `check if the user is not logged in`() = runBlocking {
        //Given
        val user = null
        coEvery { userRepository.verifyLogin() } returns user
        //When
        val response = verifyLogInUseCase()
        //Then
        coVerify(exactly = 1) { userRepository.verifyLogin() }
        assert(response == user)
    }
}
