package com.sapuglha.coroutinesexploration.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun sessionComponent(): SessionComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun appModule(module: AppModule): Builder

        fun build(): AppComponent
    }
}
