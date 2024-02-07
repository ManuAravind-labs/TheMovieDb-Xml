package com.android4you.moviedb.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android4you.moviedb.BuildConfig
import com.android4you.moviedb.data.remote.ApiService
import com.android4you.moviedb.data.response.tvshows.TVResult

class TVShowDataSource(private val apiService: ApiService, private val type: String) :
    PagingSource<Int, TVResult>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVResult> {
        return try {
            val position = params.key ?: 1
            val response = apiService.getPopularTVShows(type, BuildConfig.API_KEY, position)
            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TVResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
