package com.android4you.moviedb.utils.extension

import android.widget.ImageView
import com.android4you.moviedb.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

fun ImageView.setImageView(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(getShimmerDrawable())
        .error(R.drawable.notfound)
        .into(this)
}

fun ImageView.setImageViewRound(url: String) {
    Glide.with(this.context)
        .load(url)
        .transform(CenterCrop(), RoundedCorners(10)) // in the my case roundingRadius = 6dp
        .placeholder(getShimmerDrawable())
        .into(this)
}

fun getShimmerDrawable(): ShimmerDrawable {
    val shimmer =
        Shimmer.ColorHighlightBuilder() // The attributes for a ShimmerDrawable is set by this builder
            .setDuration(1600) // how long the shimmering animation takes to do one full sweep
            .setBaseAlpha(0.8f) // the alpha of the underlying children
            .setHighlightAlpha(0.9f) // the shimmer alpha amount
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .build()

    // This is the placeholder for the imageView
    return ShimmerDrawable()
        .apply {
            setShimmer(shimmer)
        }
}
