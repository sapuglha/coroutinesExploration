package com.sapuglha.coroutinesexploration.presentation.user.list.di

import androidx.lifecycle.ViewModel
import com.sapuglha.coroutinesexploration.presentation.ViewModelKey
import com.sapuglha.coroutinesexploration.presentation.user.list.UserListFragment
import com.sapuglha.coroutinesexploration.presentation.user.list.UserListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class Module {
    @Scope
    @ContributesAndroidInjector
    abstract fun provideListInjector(): UserListFragment

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    internal abstract fun bindListViewModel(viewModel: UserListViewModel): ViewModel
}
