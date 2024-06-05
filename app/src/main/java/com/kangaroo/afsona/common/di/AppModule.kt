package com.kangaroo.afsona.common.di

import android.content.Context
import androidx.room.Room
import com.kangaroo.afsona.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "afsona"
        ).createFromAsset("database/afsona")
            .build()
    }

    @Provides
    @Singleton
    fun provideStoryDao(database: AppDatabase) = database.storyDao()

}