package com.android4you.moviedb.presentation.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android4you.moviedb.data.response.people.PeopleResult
import com.android4you.moviedb.domain.usecase.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val getPeopleUseCase: GetPeopleUseCase,
) : ViewModel() {

    private val _selectedPeopleInfoFlow =
        MutableStateFlow<PeopleResult?>(null)
    val selectedPeopleInfoFlow: StateFlow<PeopleResult?> = _selectedPeopleInfoFlow

    init {
        getActors()
    }

    fun getActors(): Flow<PagingData<PeopleResult>> {
        return getPeopleUseCase.execute().cachedIn(viewModelScope)
    }

    fun setDetailsInfo(result: PeopleResult) {
        _selectedPeopleInfoFlow.value = result
    }
}
