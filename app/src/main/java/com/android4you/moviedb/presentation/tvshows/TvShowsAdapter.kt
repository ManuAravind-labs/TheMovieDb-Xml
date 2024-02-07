package com.android4you.moviedb.presentation.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android4you.moviedb.BuildConfig
import com.android4you.moviedb.data.response.tvshows.TVResult
import com.android4you.moviedb.databinding.ItemMovieLayoutBinding
import com.android4you.moviedb.utils.extension.setImageView

class TvShowsAdapter(val listener: (TVResult) -> Unit) :
    PagingDataAdapter<TVResult, TvShowsAdapter.TVshowViewHolder>(TVshowComparator) {

    override fun onBindViewHolder(holder: TVshowViewHolder, position: Int) {
        val movie = getItem(position)!!
        holder.view.imageView.setImageView(BuildConfig.IMAGE_URL_SMALL + movie.poster_path)
        holder.view.cardview.setOnClickListener {
            listener(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVshowViewHolder {
        val binding = ItemMovieLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return TVshowViewHolder(binding)
    }

    class TVshowViewHolder(val view: ItemMovieLayoutBinding) : RecyclerView.ViewHolder(view.root)

    object TVshowComparator : DiffUtil.ItemCallback<TVResult>() {
        override fun areItemsTheSame(oldItem: TVResult, newItem: TVResult): Boolean {
            return oldItem.original_name == newItem.original_name
        }

        override fun areContentsTheSame(oldItem: TVResult, newItem: TVResult): Boolean {
            return oldItem == newItem
        }
    }
}
