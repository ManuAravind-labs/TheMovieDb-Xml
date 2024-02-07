package com.android4you.moviedb.data.di

import android.content.Context
import com.android4you.moviedb.BuildConfig.BASE_URL
import com.android4you.moviedb.BuildConfig.BUILD_TYPE
import com.android4you.moviedb.data.interceptors.NoConnectionInterceptor
import com.android4you.moviedb.data.local.MovieDbDataBase
import com.android4you.moviedb.data.local.MoviesDao
import com.android4you.moviedb.data.local.RemoteKeysDao
import com.android4you.moviedb.data.remote.ApiService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(noConnectionInterceptor: NoConnectionInterceptor): OkHttpClient {
        BUILD_TYPE
        val builder = OkHttpClient.Builder()
            .addInterceptor(noConnectionInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit2(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesApiInterface(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesDB(@ApplicationContext context: Context): MovieDbDataBase {
        return MovieDbDataBase.getDatabase(context)
    }


    @Singleton
    @Provides
    fun providesKeysDao(appDataBase: MovieDbDataBase): RemoteKeysDao = appDataBase.remoteKeysDao

    @Singleton
    @Provides
    fun providesDao(appDataBase: MovieDbDataBase): MoviesDao = appDataBase.moviesDao
}
