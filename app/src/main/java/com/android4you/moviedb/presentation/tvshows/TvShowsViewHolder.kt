package com.android4you.moviedb.presentation.tvshows

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android4you.moviedb.R

class TvShowsViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    val textView: TextView = ItemView.findViewById<TextView>(R.id.textView)
}