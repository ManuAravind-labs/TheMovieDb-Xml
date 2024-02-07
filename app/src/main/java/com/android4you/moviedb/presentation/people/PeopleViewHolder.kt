package com.android4you.moviedb.presentation.people

import androidx.recyclerview.widget.RecyclerView
import com.android4you.moviedb.BuildConfig
import com.android4you.moviedb.data.response.people.PeopleResult
import com.android4you.moviedb.databinding.ItemPeopleLayoutBinding
import com.android4you.moviedb.utils.extension.setImageViewRound

class PeopleViewHolder(val binding: ItemPeopleLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: PeopleResult, onClick: (PeopleResult) -> Unit) {
        binding.apply {
            item.profile_path?.let {
                imageView.setImageViewRound(BuildConfig.IMAGE_URL_SMALL + it)
            }
            peopleTitleTextview.text = item.name
            root.setOnClickListener {
                onClick(item)
            }
        }
    }
}
