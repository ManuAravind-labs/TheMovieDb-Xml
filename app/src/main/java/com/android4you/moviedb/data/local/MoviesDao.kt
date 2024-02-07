package com.android4you.moviedb.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android4you.moviedb.data.response.MovieResult

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(list: List<MovieResult>)

    @Query("SELECT * FROM MovieResult")
    fun getMovies(): PagingSource<Int, MovieResult>

    @Query("Delete FROM MovieResult")
    suspend fun deleteAllImages()

    @Query("SELECT * FROM MovieResult")
    fun getMoviesTest(): List<MovieResult>

    @Query("DELETE FROM MovieResult")
    suspend fun clearRepos()

    @Query("SELECT COUNT(id) from MovieResult")
    suspend fun count(): Int
}
