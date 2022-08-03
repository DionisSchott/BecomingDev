package com.dionis.auladokevyn.base

import com.dionis.auladokevyn.domain.usecase.HomeUseCase
import com.dionis.auladokevyn.infrastructure.interfaces.usecase.IHomeUseCase
import com.dionis.auladokevyn.infrastructure.interfaces.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun ProvideHomeUseCase(homeRepository: HomeRepository): IHomeUseCase {
        return HomeUseCase(homeRepository)
    }

}