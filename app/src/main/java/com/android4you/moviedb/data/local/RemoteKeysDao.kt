package com.android4you.moviedb.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE id LIKE :id")
    suspend fun getNextPageKey(id: String): RemoteKeys?

    @Query("DELETE FROM remote_keys")
    suspend fun clearAll()
}

