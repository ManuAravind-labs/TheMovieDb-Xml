package com.android4you.moviedb.presentation.tvshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android4you.moviedb.data.response.tvshows.TVResult
import com.android4you.moviedb.domain.usecase.GetTVShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TVshowsViewModel @Inject constructor(
    private val getPagingMovies: GetTVShowsUseCase,
) : ViewModel() {

    fun getTVShows(type: String): Flow<PagingData<TVResult>> {
        return getPagingMovies.execute(type).cachedIn(viewModelScope)
    }
}
