package com.sapuglha.coroutinesexploration.presentation.list.di

import androidx.lifecycle.ViewModel
import com.sapuglha.coroutinesexploration.presentation.ViewModelKey
import com.sapuglha.coroutinesexploration.presentation.list.ListActivity
import com.sapuglha.coroutinesexploration.presentation.list.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class Module {
    @ContributesAndroidInjector
    abstract fun provideListActivityInjector(): ListActivity

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    internal abstract fun bindListViewModel(viewModel: ListViewModel): ViewModel
}