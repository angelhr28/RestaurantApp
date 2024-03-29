package com.example.restaurantapp.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import androidx.appcompat.R.interpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.restaurantapp.databinding.FragmentBoardsBinding
import com.example.restaurantapp.domain.model.Board
import com.example.restaurantapp.ui.view.adapter.BoardAdapter
import com.example.restaurantapp.ui.view.screen.RequestActivity
import com.example.restaurantapp.ui.viewmodel.BoardViewModel
import com.example.restaurantapp.utils.enums.Status
import com.example.restaurantapp.utils.makeSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class BoardsFragment : Fragment() {

    private var _binding: FragmentBoardsBinding? = null
    private val binding get() = _binding!!
    private val boardViewModel: BoardViewModel by viewModels()
    private lateinit var boardAdapter: BoardAdapter
    private var numberOfColumns = 2

    private var stateFabMenu: Boolean = false
    private var stateFabDelete: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBoardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpFab()
        observersBinding()
    }

    private fun observersBinding() {
        boardViewModel.onCreate()

        boardViewModel.boards.observe(viewLifecycleOwner) {
            boardAdapter.setItems(it)
        }
        boardViewModel.isProgress.observe(viewLifecycleOwner) {

        }
        boardViewModel.snackbar.observe(viewLifecycleOwner) {
            binding.root.makeSnackbar(it) {}
        }
        boardViewModel.createBoard.observe(viewLifecycleOwner) {
            boardAdapter.addItem(Board(it, Status.DISABLE, flgDelete = stateFabDelete))
        }
        boardViewModel.updateBoard.observe(viewLifecycleOwner) {

        }
        boardViewModel.deleteBoard.observe(viewLifecycleOwner) {
            boardAdapter.delete(it)
        }
    }

    private fun setUpFab() {
        binding.apply {
            fabMenu.setOnClickListener {
                stateFabMenu = !stateFabMenu
                fabAnimation(stateFabMenu)
            }
            fabAdd.setOnClickListener {
                val newBoard = Board("", Status.DISABLE)
                boardViewModel.add(newBoard)
            }
            fabDelete.setOnClickListener {
                stateFabDelete = !stateFabDelete
                boardAdapter.showStateDelete(stateFabDelete)
            }
        }
    }

    private fun fabAnimation(state: Boolean) {
        val interpolator: Interpolator =
            AnimationUtils.loadInterpolator(this@BoardsFragment.context,
                interpolator.fast_out_slow_in)
        binding.apply {
            fabMenu.animate()
                .rotation(if (state) 45f else 0f)
                .setInterpolator(interpolator)
                .start()
            fabAdd.animate().translationX(0F)
                .translationY(if (state) -280f else 0F).duration = 500
            fabDelete.animate().translationX(0F)
                .translationY(if (state) -150F else 0F).duration = 500
        }

        viewLifecycleOwner.lifecycleScope.launch {
            delay(if (state) 250 else 0)
            withContext(Dispatchers.Main) {
                if (state) upAnimation() else downAnimation()
            }
        }
    }

    private fun downAnimation() {
        binding.fabAdd.hide()
        binding.fabDelete.hide()
        stateFabDelete = false
        boardAdapter.showStateDelete(stateFabDelete)
    }

    private fun upAnimation() {
        binding.fabAdd.show()
        binding.fabDelete.show()
    }

    private fun setUpRecyclerView() {
        boardAdapter = BoardAdapter() {
            onAction(it)
        }
        binding.rvBoard.apply {
            adapter = boardAdapter
            setHasFixedSize(false)
            layoutManager = GridLayoutManager(context, numberOfColumns)
        }
    }

    private fun onAction(action: BoardListener) {
        when (action) {
            is BoardListener.CreateRequest -> toRequest(action.id)
            is BoardListener.DeleteBoard -> deleteBoard(action.id)
            is BoardListener.SaleRequest -> action.id
            is BoardListener.AddRequest -> action.id
        }
    }

    private fun deleteBoard(id: String) {
        boardViewModel.delete(id)
    }

    private fun toRequest(id: String) {
        val intent = Intent(context, RequestActivity::class.java)
        intent.putExtra("board_id", id)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

sealed interface BoardListener {
    class CreateRequest(val id: String) : BoardListener
    class DeleteBoard(val id: String) : BoardListener
    class SaleRequest(val id: String) : BoardListener
    class AddRequest(val id: String) : BoardListener
}
