package com.android4you.moviedb.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android4you.moviedb.BuildConfig
import com.android4you.moviedb.data.response.MovieResult
import com.android4you.moviedb.databinding.ItemMoviesHomeLayoutBinding
import com.bumptech.glide.Glide


class HomeViewHolder(private val binding: ItemMoviesHomeLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieResult) {
        binding.apply {
            Glide
                .with(imageView.context)
                .load(BuildConfig.IMAGE_URL_SMALL + item.poster_path)
                .centerCrop()
                .into(imageView)
            imageView
        }
    }

    companion object {
        fun from(parent: ViewGroup): HomeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemMoviesHomeLayoutBinding.inflate(layoutInflater, parent, false)
            return HomeViewHolder(binding)
        }
    }
}

