package com.android4you.moviedb.data.di

import com.android4you.moviedb.data.remote.ApiService
import com.android4you.moviedb.data.repository.MovieRepositoryImpl
import com.android4you.moviedb.data.repository.PeopleRepositoryImpl
import com.android4you.moviedb.data.repository.TvRepositoryImpl
import com.android4you.moviedb.domain.repository.MovieRepository
import com.android4you.moviedb.domain.repository.PeopleRepository
import com.android4you.moviedb.domain.repository.TvRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        apiService: ApiService,
    ): MovieRepository {
        return MovieRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providePeopleRepository(
        apiService: ApiService,
    ): PeopleRepository {
        return PeopleRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideTVShowRepository(
        apiService: ApiService,
    ): TvRepository {
        return TvRepositoryImpl(apiService)
    }
}
