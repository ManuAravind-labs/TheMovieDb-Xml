package com.android4you.moviedb.presentation.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.android4you.moviedb.data.response.people.PeopleResult
import com.android4you.moviedb.databinding.ItemPeopleLayoutBinding

class PeopleAdapter(val listener: (PeopleResult) -> Unit) :
    PagingDataAdapter<PeopleResult, PeopleViewHolder>(PeopleComparator) {

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val actor = getItem(position)!!
        holder.bind(actor) {
            listener(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val binding =
            ItemPeopleLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeopleViewHolder(binding)
    }

    object PeopleComparator : DiffUtil.ItemCallback<PeopleResult>() {
        override fun areItemsTheSame(oldItem: PeopleResult, newItem: PeopleResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PeopleResult, newItem: PeopleResult): Boolean {
            return oldItem == newItem
        }
    }
}
