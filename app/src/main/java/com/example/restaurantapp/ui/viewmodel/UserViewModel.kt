package com.example.restaurantapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.data.UserRepository
import com.example.restaurantapp.data.model.UserModel
import com.example.restaurantapp.domain.model.User
import com.example.restaurantapp.utils.exceptionFirebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository,
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
                repository.logIn(email, password)
                repository.saveProfile(email)
                isLogIn.postValue(true)
            } catch (e: Exception) {
                snackbar.postValue(exceptionFirebase(e) ?: "")
            }
            isProgress.postValue(false)
        }
    }

    fun isLogIn() {
        repository.currentUser()?.let {
            isLogIn.postValue(true)
        }
    }

    fun checkIn(user: UserModel) {
        viewModelScope.launch {
            isProgress.postValue(true)
            try {
                repository.checkInAuth(user)
                repository.checkInFirestore(user)
                repository.saveProfile(user.email)
                isLogIn.postValue(true)
            } catch (e: Exception) {
                snackbar.postValue(e.message)
            }
            isProgress.postValue(false)
        }
    }

    fun logOut() {
        repository.logOut()
        isLogOut.postValue(true)
    }

    fun profile() {
        repository.getProfile().apply {
            user.postValue(this)
        }
    }
}