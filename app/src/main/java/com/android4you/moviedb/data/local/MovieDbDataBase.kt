package com.android4you.moviedb.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android4you.moviedb.data.response.MovieResult

@Database(
    entities = [MovieResult::class, RemoteKeys::class],
    version = 1,
    exportSchema = false,
)
abstract class MovieDbDataBase : RoomDatabase() {
    abstract val moviesDao: MoviesDao
    abstract val remoteKeysDao: RemoteKeysDao

    companion object {
        @Volatile
        private var instance: MovieDbDataBase? = null

        fun getDatabase(context: Context): MovieDbDataBase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        // Build a local database to store data
        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, MovieDbDataBase::class.java, "movie_db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
