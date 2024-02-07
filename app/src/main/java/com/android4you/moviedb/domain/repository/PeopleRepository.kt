package com.android4you.moviedb.domain.repository

import androidx.paging.PagingData
import com.android4you.moviedb.data.NetworkState
import com.android4you.moviedb.data.response.MovieResult
import com.android4you.moviedb.data.response.people.PeopleResult
import com.android4you.moviedb.data.response.people.PopularPeopleResponse
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {

    fun getSearchResultStream(): Flow<PagingData<MovieResult>>

    suspend fun getPopularPeople(page: Int): Flow<NetworkState<PopularPeopleResponse>>

    fun getPopularActors(): Flow<PagingData<PeopleResult>>
}
