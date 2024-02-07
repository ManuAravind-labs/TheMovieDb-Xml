package com.android4you.moviedb.domain.usecase

import androidx.paging.PagingData
import com.android4you.moviedb.data.response.people.PeopleResult
import com.android4you.moviedb.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleUseCase @Inject constructor(
    private val appRepository: PeopleRepository,
) {
    fun execute(): Flow<PagingData<PeopleResult>> {
        return appRepository.getPopularActors()
    }
}
