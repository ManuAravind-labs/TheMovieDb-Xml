package com.android4you.moviedb.presentation.common

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TabLayoutAdapter(private val mList: List<String>, val listener: (Int) -> Unit) :
    RecyclerView.Adapter<TabViewHolder>() {
    private var selectedItemPosition: Int = 0

    fun setSelectedPosition(value: Int) {
        selectedItemPosition = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabViewHolder {
        return TabViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TabViewHolder, position: Int) {
        holder.bind(mList[position], position, selectedItemPosition) {
            selectedItemPosition = position
            listener(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}
