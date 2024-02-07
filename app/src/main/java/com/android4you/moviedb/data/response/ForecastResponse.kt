package com.android4you.moviedb.data.response

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("coord")
    val coord: Coord,

)
