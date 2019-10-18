package com.sapuglha.coroutinesexploration.presentation.location.di

import androidx.lifecycle.ViewModel
import com.sapuglha.coroutinesexploration.presentation.ViewModelKey
import com.sapuglha.coroutinesexploration.presentation.location.LocationFragment
import com.sapuglha.coroutinesexploration.presentation.location.LocationViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class Module {
    @Scope
    @ContributesAndroidInjector
    abstract fun provideInjector(): LocationFragment

    @Binds
    @IntoMap
    @ViewModelKey(LocationViewModel::class)
    internal abstract fun bindViewModel(viewModel: LocationViewModel): ViewModel
}

