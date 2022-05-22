package com.example.restaurantapp.ui.view.viewholder.interfaces

import com.example.restaurantapp.domain.model.Board

interface BoardViewHolder {
    fun bind(board: Board?, position: Int, listener: (String) -> Unit)
}