package com.sapuglha.coroutinesexploration.di

import com.sapuglha.coroutinesexploration.App
import com.sapuglha.coroutinesexploration.data.di.DataModule
import com.sapuglha.coroutinesexploration.data.di.NetModule
import com.sapuglha.coroutinesexploration.data.di.RepositoryModule
import dagger.Subcomponent


@Subcomponent(
    modules = [
        DataModule::class,
        RepositoryModule::class,
        NetModule::class,

        // List modules for each activity/fragment/viewModel group
        com.sapuglha.coroutinesexploration.presentation.user.list.di.Module::class,
        com.sapuglha.coroutinesexploration.presentation.user.form.di.Module::class
    ]
)
interface SessionComponent {

    fun inject(app: App)

    @Subcomponent.Builder
    interface Builder {
        fun build(): SessionComponent
    }
}
