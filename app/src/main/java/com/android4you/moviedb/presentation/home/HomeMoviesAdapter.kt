package com.android4you.moviedb.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android4you.moviedb.data.response.MovieResult
import com.android4you.moviedb.presentation.movies.adapter.MoviesListViewHolder

class HomeMoviesAdapter : RecyclerView.Adapter<HomeViewHolder>() {
    private val mList: ArrayList<MovieResult> = ArrayList()
    fun submitList(newList: List<MovieResult>) {
        mList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val str = mList[position]
        holder.bind(str)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}
