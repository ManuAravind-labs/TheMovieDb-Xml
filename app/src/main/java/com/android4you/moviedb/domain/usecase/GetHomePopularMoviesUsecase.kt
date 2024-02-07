package com.android4you.moviedb.domain.usecase

import com.android4you.moviedb.data.NetworkState
import com.android4you.moviedb.data.response.MovieResponse
import com.android4you.moviedb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomePopularMoviesUsecase @Inject constructor(
    private val appRepository: MovieRepository,
) {
    suspend fun execute(type: String): Flow<NetworkState<MovieResponse>> {
        return appRepository.getPopularMovies(1, type)
    }
}
