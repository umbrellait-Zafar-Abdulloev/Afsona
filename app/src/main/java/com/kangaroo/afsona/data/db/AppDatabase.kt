package com.kangaroo.afsona.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kangaroo.afsona.domain.model.Story

@Database(entities = [Story::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun storyDao(): StoryDao
}