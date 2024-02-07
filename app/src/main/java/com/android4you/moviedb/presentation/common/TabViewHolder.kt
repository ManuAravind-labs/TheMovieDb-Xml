package com.android4you.moviedb.presentation.common

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android4you.moviedb.databinding.ItemCategoryTabBinding

class TabViewHolder(private val binding: ItemCategoryTabBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String, position: Int, selectedItemPosition: Int, listener: (Int) -> Unit) {
        binding.apply {
            if (selectedItemPosition == position) {
                indicator.setBackgroundColor(Color.parseColor("#FF133364"))
            } else {
                indicator.setBackgroundColor(Color.TRANSPARENT)
            }

            tabTitle.text = item
            tabView.setOnClickListener {
                if (position != selectedItemPosition) {
                    listener(position)
                }
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): TabViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemCategoryTabBinding.inflate(layoutInflater, parent, false)
            return TabViewHolder(binding)
        }
    }
}
