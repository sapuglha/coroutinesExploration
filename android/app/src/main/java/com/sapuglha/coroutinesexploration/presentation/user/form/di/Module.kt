package com.sapuglha.coroutinesexploration.presentation.user.form.di

import androidx.lifecycle.ViewModel
import com.sapuglha.coroutinesexploration.presentation.ViewModelKey
import com.sapuglha.coroutinesexploration.presentation.user.form.UserFormFragment
import com.sapuglha.coroutinesexploration.presentation.user.form.UserFormViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class Module {
    @Scope
    @ContributesAndroidInjector
    abstract fun provideFormActivityInjector(): UserFormFragment

    @Binds
    @IntoMap
    @ViewModelKey(UserFormViewModel::class)
    internal abstract fun bindFormViewModel(viewModel: UserFormViewModel): ViewModel
}
