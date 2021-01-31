package com.example.pagingsample.di

import android.content.Context
import androidx.room.Room
import com.example.pagingsample.data.AppDatabase
import com.example.pagingsample.data.dao.MainDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(
                context,
                AppDatabase::class.java,
                "app-db.db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMainDao(database: AppDatabase): MainDao {
        return database.getMainDao()
    }
}