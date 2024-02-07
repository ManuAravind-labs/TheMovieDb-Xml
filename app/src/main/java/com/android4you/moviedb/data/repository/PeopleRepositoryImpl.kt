package com.android4you.moviedb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android4you.moviedb.BuildConfig
import com.android4you.moviedb.data.NetworkState
import com.android4you.moviedb.data.datasource.ActorsDataSource
import com.android4you.moviedb.data.datasource.MoviesDataSource
import com.android4you.moviedb.data.remote.ApiService
import com.android4you.moviedb.data.response.MovieResult
import com.android4you.moviedb.data.response.people.PeopleResult
import com.android4you.moviedb.data.response.people.PopularPeopleResponse
import com.android4you.moviedb.domain.repository.PeopleRepository
import com.android4you.moviedb.utils.common.MovieEnum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : PeopleRepository {

    override suspend fun getPopularPeople(
        page: Int,
    ): Flow<NetworkState<PopularPeopleResponse>> {
        return flow {
            try {
                val response = apiService.getPopularPeople(
                    api_key = BuildConfig.API_KEY,
                    page = page,
                )
                if (response.isSuccessful) {
                    emit(NetworkState.Success(response.body()!!))
                } else {
                    emit(NetworkState.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(NetworkState.Error(e.message))
            }
        }
    }

    override fun getSearchResultStream(): Flow<PagingData<MovieResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { MoviesDataSource(apiService, MovieEnum.NOWPLAYING.value) },
        ).flow
    }

    override fun getPopularActors(): Flow<PagingData<PeopleResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { ActorsDataSource(apiService) },
        ).flow
    }
}
