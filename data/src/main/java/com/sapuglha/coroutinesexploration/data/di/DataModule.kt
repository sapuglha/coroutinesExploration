package com.sapuglha.coroutinesexploration.data.di

import android.content.Context
import com.sapuglha.coroutinesexploration.data.db.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideInMemoryDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context)
}
