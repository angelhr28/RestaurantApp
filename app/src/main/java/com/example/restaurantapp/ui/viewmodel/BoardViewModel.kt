package com.example.restaurantapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.domain.model.Board
import com.example.restaurantapp.domain.usecase.board.AddBoardUseCase
import com.example.restaurantapp.domain.usecase.board.DeleteBoardUseCase
import com.example.restaurantapp.domain.usecase.board.GetBoardUseCase
import com.example.restaurantapp.utils.exceptionFirebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val getBoardUseCase: GetBoardUseCase,
    private val addBoardUseCase: AddBoardUseCase,
    private val deleteBoardUseCase: DeleteBoardUseCase,
) : ViewModel() {

    val isProgress = MutableLiveData<Boolean>()
    val snackbar = MutableLiveData<String>()
    val boards = MutableLiveData<List<Board?>>()


    fun onCreate() {
        viewModelScope.launch {
            isProgress.postValue(true)
            try {
                val listBoards = getBoardUseCase()
                boards.postValue(listBoards)
            } catch (e: Exception) {
                snackbar.postValue(exceptionFirebase(e) ?: "")
            }
            isProgress.postValue(false)
        }
    }

    fun add(board: Board) {
        viewModelScope.launch {
            addBoardUseCase(board)
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            deleteBoardUseCase(id)
        }
    }

}