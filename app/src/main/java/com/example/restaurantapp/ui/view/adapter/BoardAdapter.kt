package com.example.restaurantapp.ui.view.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.domain.model.Board
import com.example.restaurantapp.ui.view.viewholder.BoardBusyViewHolder
import com.example.restaurantapp.ui.view.viewholder.BoardFreeViewHolder
import com.example.restaurantapp.ui.view.viewholder.interfaces.BoardViewHolder
import com.example.restaurantapp.utils.enums.Status

class BoardAdapter(private val listener: (String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<Board?> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Board?>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: Board) {
        this.items.add(item)
        notifyItemInserted(this.items.size - 1)
    }

    fun getItem(position: Int): Board? = this.items[position]

    fun delete(position: Int) {
        this.items.removeAt(position)
        notifyItemRemoved(position);
    }

    override fun getItemCount() = this.items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as BoardViewHolder).bind(this.items[position], position + 1, listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Status.DISABLE.code -> BoardFreeViewHolder.from(parent)
            Status.ENABLE.code -> BoardBusyViewHolder.from(parent)
            else -> BoardFreeViewHolder.from(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position]?.status?.code ?: Status.DISABLE.code
    }

}