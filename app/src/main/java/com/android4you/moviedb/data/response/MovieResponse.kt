package com.android4you.moviedb.data.response

data class MovieResponse(
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)