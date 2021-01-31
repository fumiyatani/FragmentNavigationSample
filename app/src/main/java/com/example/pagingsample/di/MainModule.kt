package com.example.pagingsample.di

import android.app.Activity
import com.example.pagingsample.data.dao.MainDao
import com.example.pagingsample.domain.MainUseCase
import com.example.pagingsample.ui.main.MainActivity
import com.example.pagingsample.ui.main.MainContract
import com.example.pagingsample.ui.main.MainPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
object MainModule {
    @Provides
    fun provideUseCase(dao: MainDao): MainUseCase {
        return MainUseCase(dao)
    }

    @Provides
    fun provideActivity(activity: Activity): MainActivity {
        return activity as? MainActivity ?: throw RuntimeException("MainActivityが存在しません")
    }

    @Provides
    fun provideView(activity: MainActivity): MainContract.View {
        return activity
    }

    @Provides
    fun providePresenter(view: MainContract.View, useCase: MainUseCase): MainContract.Presenter {
        return MainPresenter(view, useCase)
    }
}