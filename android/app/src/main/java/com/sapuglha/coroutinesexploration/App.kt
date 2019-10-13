package com.sapuglha.coroutinesexploration

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.sapuglha.coroutinesexploration.di.AppComponent
import com.sapuglha.coroutinesexploration.di.AppModule
import com.sapuglha.coroutinesexploration.di.DaggerAppComponent
import com.sapuglha.coroutinesexploration.di.SessionComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @set:VisibleForTesting
    lateinit var component: AppComponent

    @Inject
    internal lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        setupDependencyInjection()
    }

    private fun setupDependencyInjection() {
        component = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .application(this)
            .build()

        sessionComponent = component.sessionComponent().build()
        sessionComponent.inject(this)
    }

    companion object {
        @set:VisibleForTesting
        lateinit var sessionComponent: SessionComponent
            internal set
    }
}
