package com.android4you.moviedb.domain.usecase

import androidx.paging.PagingData
import com.android4you.moviedb.data.response.MovieResult
import com.android4you.moviedb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val appRepository: MovieRepository,
) {
    fun execute(type: String): Flow<PagingData<MovieResult>> {
        return appRepository.getSearchResultStream(type)
    }
}
