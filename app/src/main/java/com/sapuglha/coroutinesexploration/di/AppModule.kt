package com.sapuglha.coroutinesexploration.di

import android.content.Context
import com.sapuglha.coroutinesexploration.App
import dagger.Module
import dagger.Provides

@Module
open class AppModule(val app: App) {
    @Provides
    fun provideApp() = app

    @Provides
    fun providesContext(): Context = app
}
