package com.android4you.moviedb.domain.repository

import androidx.paging.PagingData
import com.android4you.moviedb.data.NetworkState
import com.android4you.moviedb.data.response.MovieResponse
import com.android4you.moviedb.data.response.MovieResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies(page: Int, type: String): Flow<NetworkState<MovieResponse>>

    fun getSearchResultStream(type: String): Flow<PagingData<MovieResult>>
}
