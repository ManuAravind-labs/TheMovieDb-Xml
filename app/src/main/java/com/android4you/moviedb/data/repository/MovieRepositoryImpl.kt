package com.android4you.moviedb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android4you.moviedb.BuildConfig.API_KEY
import com.android4you.moviedb.data.NetworkState
import com.android4you.moviedb.data.datasource.MoviesDataSource
import com.android4you.moviedb.data.remote.ApiService
import com.android4you.moviedb.data.response.MovieResponse
import com.android4you.moviedb.data.response.MovieResult
import com.android4you.moviedb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : MovieRepository {

    override suspend fun getPopularMovies(
        page: Int,
        type: String,
    ): Flow<NetworkState<MovieResponse>> {
        return flow {
            val response = apiService.getPopularMovies(
                type,
                api_key = API_KEY,
                page = page,
            )
            if (response.isSuccessful) {
                emit(NetworkState.Success(response.body()!!))
            } else {
                emit(NetworkState.Error(response.message()))
            }
        }
    }

    override fun getSearchResultStream(type: String): Flow<PagingData<MovieResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { MoviesDataSource(apiService, type) },
        ).flow
    }
}
