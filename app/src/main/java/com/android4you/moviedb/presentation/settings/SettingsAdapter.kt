package com.android4you.moviedb.presentation.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android4you.moviedb.BuildConfig
import com.android4you.moviedb.data.response.MovieResult
import com.android4you.moviedb.databinding.ItemMovieLayoutBinding
import com.bumptech.glide.Glide

class SettingsAdapter() :
    RecyclerView.Adapter<SettingsViewHolder>() {
    private val mList: ArrayList<MovieResult> = ArrayList()
    fun submitList(newList: ArrayList<MovieResult>) {
        mList.addAll(newList)
        notifyDataSetChanged()
    }

    fun clearAll() {
        mList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        return SettingsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val str = mList[position]
        holder.bind(str)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}

class SettingsViewHolder(private val binding: ItemMovieLayoutBinding) :
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
        fun from(parent: ViewGroup): SettingsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemMovieLayoutBinding.inflate(layoutInflater, parent, false)
            return SettingsViewHolder(binding)
        }
    }
}
