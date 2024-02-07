package com.android4you.moviedb.domain.usecase

import androidx.paging.PagingData
import com.android4you.moviedb.data.response.tvshows.TVResult
import com.android4you.moviedb.domain.repository.TvRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTVShowsUseCase @Inject constructor(
    private val appRepository: TvRepository,
) {
    fun execute(type: String): Flow<PagingData<TVResult>> {
        return appRepository.getSearchResultStream(type)
    }
}
