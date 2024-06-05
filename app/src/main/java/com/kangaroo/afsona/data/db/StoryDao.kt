package com.kangaroo.afsona.data.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.kangaroo.afsona.domain.model.Story

@Dao
interface StoryDao {

    @Query("SELECT * FROM story")
    fun getAll(): PagingSource<Int, Story>

    @Query("UPDATE story SET fav = :isFav WHERE _id = :id")
    suspend fun setFav(id: Int, isFav: Boolean)

    @Query("SELECT * FROM story WHERE _id = :id")
    fun getById(id: Int): LiveData<Story>

    @Query("SELECT * FROM story WHERE _id = :id")
    suspend fun getByIdAsync(id: Int): Story

    @Query("SELECT * FROM story WHERE fav = 1")
    fun getAllFavorites(): PagingSource<Int, Story>

    @Query("SELECT * FROM story WHERE title LIKE '%' || :query || '%'")
    suspend fun search(query: String): List<Story>

}