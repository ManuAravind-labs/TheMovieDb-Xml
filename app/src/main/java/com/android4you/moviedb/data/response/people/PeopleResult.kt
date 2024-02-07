package com.android4you.moviedb.data.response.people

data class PeopleResult(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
   // val known_for: List<KnownFor>,
    val known_for_department: String,
    val name: String,
    val popularity: Double,
    val profile_path: String?
)