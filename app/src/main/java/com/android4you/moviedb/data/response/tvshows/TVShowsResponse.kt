package com.android4you.moviedb.data.response.tvshows

data class TVShowsResponse(
    val page: Int,
    val results: List<TVResult>,
    val total_pages: Int,
    val total_results: Int
)