package com.android4you.moviedb.presentation.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android4you.moviedb.data.response.MovieResult
import com.android4you.moviedb.databinding.ItemMovieLayoutBinding

class MoviesNewAdapter() : ListAdapter<MovieResult, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieNewViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val workout = getItem(position)
        if (holder is MovieNewViewHolder) {
            holder.bind(workout)
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<MovieResult> =
            object : DiffUtil.ItemCallback<MovieResult>() {
                override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult) =
                    oldItem.title == newItem.title

                override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult) =
                    oldItem == newItem
            }
    }
}

class MovieNewViewHolder(private val binding: ItemMovieLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieResult) {
        binding.apply {
        }
    }

    companion object {
        fun from(parent: ViewGroup): MovieNewViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemMovieLayoutBinding.inflate(layoutInflater, parent, false)
            return MovieNewViewHolder(binding)
        }
    }
}
