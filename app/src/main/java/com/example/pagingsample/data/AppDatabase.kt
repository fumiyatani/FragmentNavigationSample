package com.example.pagingsample.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pagingsample.data.dao.MainDao
import com.example.pagingsample.data.entity.FirstEntity
import com.example.pagingsample.data.entity.SecondEntity
import com.example.pagingsample.data.entity.ThirdEntity

@Database(
    entities = [FirstEntity::class, SecondEntity::class, ThirdEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMainDao(): MainDao
}