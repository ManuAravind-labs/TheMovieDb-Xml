package com.android4you.moviedb.presentation.movies.viewstates

import com.android4you.moviedb.data.response.MovieResponse

sealed class MoviesViewState {
    object Error : MoviesViewState()
    object Empty : MoviesViewState()
    data class Loading(val isLoading: Boolean) : MoviesViewState()
    data class Success(val data: MovieResponse?) :
        MoviesViewState()
}
