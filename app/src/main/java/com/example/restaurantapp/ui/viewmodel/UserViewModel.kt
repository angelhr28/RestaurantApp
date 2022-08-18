package com.example.restaurantapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.data.model.UserModel
import com.example.restaurantapp.domain.model.User
import com.example.restaurantapp.domain.usecase.user.*
import com.example.restaurantapp.utils.exceptionFirebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val loginUseCase: LogInUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val checkInUserUseCase: CheckInUserUseCase,
    private val profileUseCase: ProfileUseCase,
    private val verifyLogInUseCase: VerifyLogInUseCase,
) : ViewModel() {

    val snackbar = MutableLiveData<String>()
    val isProgress = MutableLiveData<Boolean>()
    val isLogIn = MutableLiveData<Boolean>()
    val isLogOut = MutableLiveData<Boolean>()
    val user = MutableLiveData<User>()

    fun logIn(email: String, password: String) {
        viewModelScope.launch {
            isProgress.postValue(true)
            try {
                loginUseCase(email, password)
                isLogIn.postValue(true)
            } catch (e: Exception) {
                snackbar.postValue(exceptionFirebase(e) ?: "")
            }
            isProgress.postValue(false)
        }
    }

    fun checkIn(user: UserModel) {
        viewModelScope.launch {
            isProgress.postValue(true)
            try {
                checkInUserUseCase(user)
                isLogIn.postValue(true)
            } catch (e: Exception) {
                snackbar.postValue(e.message)
            }
            isProgress.postValue(false)
        }
    }

    fun logOut() {
        viewModelScope.launch {
            logOutUseCase()
            isLogOut.postValue(true)
        }
    }

    fun isLogIn() {
        verifyLogInUseCase()?.let {
            isLogIn.postValue(true)
        } ?: kotlin.run {
            isLogIn.postValue(false)
        }
    }

    fun profile() {
        profileUseCase()?.let {
            user.postValue(it)
        }
    }
}