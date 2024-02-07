package com.android4you.moviedb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android4you.moviedb.data.datasource.TVShowDataSource
import com.android4you.moviedb.data.remote.ApiService
import com.android4you.moviedb.data.response.tvshows.TVResult
import com.android4you.moviedb.domain.repository.TvRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : TvRepository {
    override fun getSearchResultStream(type: String): Flow<PagingData<TVResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { TVShowDataSource(apiService, type) },
        ).flow
    }
}
