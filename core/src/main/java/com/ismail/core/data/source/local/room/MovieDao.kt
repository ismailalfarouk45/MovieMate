package com.ismail.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ismail.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert
    suspend fun insertMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM favorite")
    fun getAllFavorite(): Flow<List<MovieEntity>>

    @Delete
    suspend fun delete(movieEntity: MovieEntity)

    @Query("SELECT EXISTS(SELECT * FROM favorite WHERE id = :id )")
    fun isFavorite(id: Int): Flow<Boolean>
}