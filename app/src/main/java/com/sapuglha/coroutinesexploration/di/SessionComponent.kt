package com.sapuglha.coroutinesexploration.di

import com.sapuglha.coroutinesexploration.App
import com.sapuglha.coroutinesexploration.data.di.DataModule
import dagger.Subcomponent


@Subcomponent(
    modules = [
        DataModule::class,

        // List modules for each activity/fragment/viewModel group
        com.sapuglha.coroutinesexploration.presentation.list.di.Module::class
    ]
)
interface SessionComponent {

    fun inject(app: App)

    @Subcomponent.Builder
    interface Builder {
        fun build(): SessionComponent
    }
}
