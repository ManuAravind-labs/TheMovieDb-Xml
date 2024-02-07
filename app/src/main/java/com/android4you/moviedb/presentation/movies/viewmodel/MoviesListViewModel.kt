package com.android4you.moviedb.presentation.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android4you.moviedb.data.response.MovieResult
import com.android4you.moviedb.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getPopularMovies: GetPopularMoviesUseCase,
) : ViewModel() {

    fun getMovies(type: String): Flow<PagingData<MovieResult>> {
        return getPopularMovies.execute(type).cachedIn(viewModelScope)
    }
}
