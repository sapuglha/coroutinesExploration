package com.sapuglha.coroutinesexploration

import android.app.Activity
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import com.sapuglha.coroutinesexploration.di.AppComponent
import com.sapuglha.coroutinesexploration.di.AppModule
import com.sapuglha.coroutinesexploration.di.DaggerAppComponent
import com.sapuglha.coroutinesexploration.di.SessionComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector, HasSupportFragmentInjector {
    @set:VisibleForTesting
    lateinit var component: AppComponent

    @Inject
    internal lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    internal lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector() = dispatchingActivityInjector

    override fun supportFragmentInjector() = dispatchingFragmentInjector

    override fun onCreate() {
        super.onCreate()

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
