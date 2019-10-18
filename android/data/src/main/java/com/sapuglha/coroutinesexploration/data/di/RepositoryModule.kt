package com.sapuglha.coroutinesexploration.data.di

import android.content.Context
import com.sapuglha.coroutinesexploration.data.net.UserApi
import com.sapuglha.coroutinesexploration.data.repository.LocationRepositoryImpl
import com.sapuglha.coroutinesexploration.data.repository.UserRepositoryImpl
import com.sapuglha.coroutinesexploration.domain.repository.LocationRepository
import com.sapuglha.coroutinesexploration.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
open class RepositoryModule {
    @Provides
    fun provideUserRepository(userApi: UserApi): UserRepository = UserRepositoryImpl(userApi)

    @Provides
    fun provideLocationRepository(context: Context): LocationRepository =
        LocationRepositoryImpl(context)
}
