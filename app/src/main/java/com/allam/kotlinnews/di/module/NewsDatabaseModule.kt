package com.allam.kotlinnews.di.module

import android.app.Application
import com.allam.kotlinnews.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@InstallIn(SingletonComponent::class)
@Module
class NewsDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = NewsDatabase.getDatabase(application)

    @Singleton
    @Provides
    fun provideNewsDao(database: NewsDatabase) = database.newsDao()
}