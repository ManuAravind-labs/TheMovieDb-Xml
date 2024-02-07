package com.android4you.moviedb.domain.repository

import androidx.paging.PagingData
import com.android4you.moviedb.data.response.tvshows.TVResult
import kotlinx.coroutines.flow.Flow

interface TvRepository {

    fun getSearchResultStream(type: String): Flow<PagingData<TVResult>>
}