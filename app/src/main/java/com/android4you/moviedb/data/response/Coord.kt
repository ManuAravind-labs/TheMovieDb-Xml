package com.android4you.moviedb.data.response

import com.google.gson.annotations.SerializedName

data class Coord(

    @SerializedName("lon") var lon: Double? = null,
    @SerializedName("lat") var lat: Double? = null,

)
