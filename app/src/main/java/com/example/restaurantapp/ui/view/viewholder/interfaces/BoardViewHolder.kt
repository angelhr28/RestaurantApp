package com.example.restaurantapp.ui.view.viewholder.interfaces

import com.example.restaurantapp.domain.model.Board
import com.example.restaurantapp.ui.view.fragment.BoardListener

interface BoardViewHolder {
    fun bind(board: Board?, position: Int, listener: (BoardListener) -> Unit)
}