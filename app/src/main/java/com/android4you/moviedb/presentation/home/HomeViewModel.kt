package com.android4you.moviedb.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android4you.moviedb.data.response.MovieResult
import com.android4you.moviedb.domain.usecase.GetHomePopularMoviesUsecase
import com.android4you.moviedb.utils.common.MovieEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMovies: GetHomePopularMoviesUsecase,
) : ViewModel() {

    private val _moviesListPopularStateFlow = MutableStateFlow<List<MovieResult>>(emptyList())

    val moviesListPopularStateFlow: StateFlow<List<MovieResult>> = _moviesListPopularStateFlow

    private val _moviesListTopRatedStateFlow = MutableStateFlow<List<MovieResult>>(emptyList())

    val moviesListTopRatedStateFlow: StateFlow<List<MovieResult>> = _moviesListTopRatedStateFlow

    private val _moviesListNowPlayingStateFlow = MutableStateFlow<List<MovieResult>>(emptyList())

    val moviesListNowPlayingStateFlow: StateFlow<List<MovieResult>> = _moviesListNowPlayingStateFlow

    private val _moviesListUpcomingStateFlow = MutableStateFlow<List<MovieResult>>(emptyList())

    val moviesListUpcomingStateFlow: StateFlow<List<MovieResult>> = _moviesListUpcomingStateFlow

    init {
        getPopularMovies(MovieEnum.POPULAR.value)
        getNowPlayingMovies(MovieEnum.NOWPLAYING.value)
        getTopRatedMovies(MovieEnum.TOPRATED.value)
        getUpcomingMovies(MovieEnum.UPCOMING.value)
    }

    private fun getPopularMovies(type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getPopularMovies.execute(type).flowOn(Dispatchers.Main)
                .catch {
                }
                .collect { result ->
                    result.data?.results?.let {
                        _moviesListPopularStateFlow.value = it
                    }
                }
        }
    }

    private fun getTopRatedMovies(type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getPopularMovies.execute(type).flowOn(Dispatchers.Main)
                .catch {
                }
                .collect { result ->
                    result.data?.results?.let {
                        _moviesListTopRatedStateFlow.value = it
                    }
                }
        }
    }

    private fun getNowPlayingMovies(type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getPopularMovies.execute(type).flowOn(Dispatchers.Main)
                .catch {
                }
                .collect { result ->
                    result.data?.results?.let {
                        _moviesListNowPlayingStateFlow.value = it
                    }
                }
        }
    }

    private fun getUpcomingMovies(type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getPopularMovies.execute(type).flowOn(Dispatchers.Main)
                .catch {
                }
                .collect { result ->
                    result.data?.results?.let {
                        _moviesListUpcomingStateFlow.value = it
                    }
                }
        }
    }
}
