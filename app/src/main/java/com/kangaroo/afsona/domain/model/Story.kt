package com.kangaroo.afsona.domain.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Story(
    @PrimaryKey
    @ColumnInfo(name = "_id")
    val id: Int,
    val title: String,
    @ColumnInfo(name = "fav")
    val isFav: Boolean,
    @ColumnInfo(name = "detail")
    val content: String,
    @ColumnInfo(name = "img")
    val image: String
)
