package com.example.restaurantapp.ui.view.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.databinding.ItemCardFreeBinding
import com.example.restaurantapp.domain.model.Board
import com.example.restaurantapp.ui.view.viewholder.interfaces.BoardViewHolder

class BoardFreeViewHolder(private val binding: ItemCardFreeBinding) :
    RecyclerView.ViewHolder(binding.root), BoardViewHolder {
    companion object {
        fun from(parent: ViewGroup): BoardFreeViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCardFreeBinding.inflate(inflater, parent, false)
            return BoardFreeViewHolder(binding)
        }
    }

    override fun bind(board: Board?, position: Int, listener: (String) -> Unit) {
        binding.apply {
            lblOrder.text = position.toString()
            root.setOnClickListener {
                board?.id?.let { it1 -> listener(it1) }
            }
        }
    }
}