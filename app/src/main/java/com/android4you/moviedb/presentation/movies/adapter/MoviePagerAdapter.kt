package com.android4you.moviedb.presentation.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android4you.moviedb.BuildConfig
import com.android4you.moviedb.data.response.MovieResult
import com.android4you.moviedb.databinding.ItemMovieLayoutBinding
import com.android4you.moviedb.utils.extension.setImageView

class MoviePagerAdapter(val listener: (MovieResult) -> Unit) :
    PagingDataAdapter<MovieResult, MoviePagerAdapter.MovieViewHolder>(MovieComparator) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)!!
        holder.view.imageView.setImageView(BuildConfig.IMAGE_URL_SMALL + movie.poster_path)
        holder.view.cardview.setOnClickListener {
            listener(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return MovieViewHolder(binding)
    }

    class MovieViewHolder(val view: ItemMovieLayoutBinding) : RecyclerView.ViewHolder(view.root)

    object MovieComparator : DiffUtil.ItemCallback<MovieResult>() {
        override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem.original_title == newItem.original_title
        }

        override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem == newItem
        }
    }
}
