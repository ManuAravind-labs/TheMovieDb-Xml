package com.android4you.moviedb.domain.usecase

import androidx.paging.PagingData
import com.android4you.moviedb.data.response.MovieResult
import com.android4you.moviedb.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagingMovies @Inject constructor(
    private val appRepository: PeopleRepository,
) {
    fun execute(): Flow<PagingData<MovieResult>> {
        return appRepository.getSearchResultStream()
    }
}
