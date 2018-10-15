package com.sapuglha.coroutinesexploration.presentation.form.di

import androidx.lifecycle.ViewModel
import com.sapuglha.coroutinesexploration.presentation.ViewModelKey
import com.sapuglha.coroutinesexploration.presentation.form.FormActivity
import com.sapuglha.coroutinesexploration.presentation.form.FormViewModel
import com.sapuglha.coroutinesexploration.presentation.list.ListActivity
import com.sapuglha.coroutinesexploration.presentation.list.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class Module {
    @ContributesAndroidInjector
    abstract fun provideFormActivityInjector(): FormActivity

    @Binds
    @IntoMap
    @ViewModelKey(FormViewModel::class)
    internal abstract fun bindFormViewModel(viewModel: FormViewModel): ViewModel
}
