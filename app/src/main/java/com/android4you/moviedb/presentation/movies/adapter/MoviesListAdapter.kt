package com.android4you.moviedb.presentation.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android4you.moviedb.BuildConfig
import com.android4you.moviedb.data.response.MovieResult
import com.android4you.moviedb.databinding.ItemMovieLayoutBinding
import com.bumptech.glide.Glide

class MoviesListAdapter :
    RecyclerView.Adapter<MoviesListViewHolder>() {
    private val mList: ArrayList<MovieResult> = ArrayList()
    fun submitList(newList: List<MovieResult>) {
        mList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        return MoviesListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        val str = mList[position]
        holder.bind(str)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}

class MoviesListViewHolder(private val binding: ItemMovieLayoutBinding) :
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
        fun from(parent: ViewGroup): MoviesListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemMovieLayoutBinding.inflate(layoutInflater, parent, false)
            return MoviesListViewHolder(binding)
        }
    }
}

