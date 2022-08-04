package com.dionis.auladokevyn.base

import com.dionis.auladokevyn.data.api.home.HomeApi
import com.dionis.auladokevyn.infrastructure.interfaces.repository.HomeRepository
import com.dionis.auladokevyn.infrastructure.repository.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesHomeRepository(homeApi: HomeApi): HomeRepository {
        return HomeRepositoryImpl(homeApi)
    }
}