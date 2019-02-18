package com.sapuglha.coroutinesexploration.data.di

import com.sapuglha.coroutinesexploration.data.db.AppDatabase
import com.sapuglha.coroutinesexploration.data.net.UserApi
import com.sapuglha.coroutinesexploration.data.repository.UserRepositoryImpl
import com.sapuglha.coroutinesexploration.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
open class RepositoryModule {
    @Provides
    fun provideUserRepository(db: AppDatabase, userApi: UserApi): UserRepository = UserRepositoryImpl(db, userApi)
}
