package com.sapuglha.coroutinesexploration.presentation.main.di

import androidx.lifecycle.ViewModel
import com.sapuglha.coroutinesexploration.presentation.ViewModelKey
import com.sapuglha.coroutinesexploration.presentation.main.MainActivity
import com.sapuglha.coroutinesexploration.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class Module {
    @ContributesAndroidInjector
    abstract fun provideMainActivityInjector(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindTripsViewModel(viewModel: MainViewModel): ViewModel
}
