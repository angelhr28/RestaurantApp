package com.example.restaurantapp.ui.view.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.domain.model.Board
import com.example.restaurantapp.ui.view.fragment.BoardListener
import com.example.restaurantapp.ui.view.viewholder.BoardBusyViewHolder
import com.example.restaurantapp.ui.view.viewholder.BoardFreeViewHolder
import com.example.restaurantapp.ui.view.viewholder.interfaces.BoardViewHolder
import com.example.restaurantapp.utils.enums.Status

class BoardAdapter(private val listener: (BoardListener) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var boards: MutableList<Board?> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Board?>) {
        boards.clear()
        boards.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: Board) {
        boards.add(item)
        notifyItemInserted(boards.size - 1)
    }

    fun getItem(position: Int): Board? = boards[position]

    fun delete(position: String) {
        val index = boards.indexOfFirst {
            it?.id == position
        }
        boards.removeAt(index)
        notifyItemRemoved(index);
    }

    fun showStateDelete(state: Boolean) {
        boards.map { item ->
            item?.let {
                if (it.status == Status.DISABLE) {
                    it.flgDelete = state
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount() = boards.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as BoardViewHolder).bind(boards[position], position + 1, listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Status.DISABLE.code -> BoardFreeViewHolder.from(parent)
            Status.ENABLE.code -> BoardBusyViewHolder.from(parent)
            else -> BoardFreeViewHolder.from(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return boards[position]?.status?.code ?: Status.DISABLE.code
    }
}