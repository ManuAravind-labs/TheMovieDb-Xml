package com.android4you.moviedb.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.android4you.moviedb.R
import com.android4you.moviedb.databinding.ItemLoadStateFooterViewBinding

class LoadStateViewHolder(private val binding: ItemLoadStateFooterViewBinding, retry: () -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text =
                TEST.ANGRY + itemView.context.getString(R.string.error_text_label)
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_load_state_footer_view, parent, false)
            val binding = ItemLoadStateFooterViewBinding.bind(view)
            return LoadStateViewHolder(binding, retry)
        }
    }
}

object TEST {
    const val ANGRY = "\uD83D\uDE28 "
}
