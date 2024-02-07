package com.android4you.moviedb.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android4you.moviedb.BuildConfig
import com.android4you.moviedb.data.remote.ApiService
import com.android4you.moviedb.data.response.people.PeopleResult

class ActorsDataSource (private val apiService: ApiService) :
    PagingSource<Int, PeopleResult>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PeopleResult> {
        return try {
            val position = params.key ?: 1
            val response = apiService.getPopularPeople(BuildConfig.API_KEY, position)
            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PeopleResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
