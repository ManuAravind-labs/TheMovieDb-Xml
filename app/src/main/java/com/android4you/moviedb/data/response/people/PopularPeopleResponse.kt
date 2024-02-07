package com.android4you.moviedb.data.response.people

data class PopularPeopleResponse(
    val page: Int,
    val results: List<PeopleResult>,
    val total_pages: Int,
    val total_results: Int
)