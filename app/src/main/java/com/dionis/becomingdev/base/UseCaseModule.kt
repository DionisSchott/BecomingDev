package com.dionis.becomingdev.base

import com.dionis.becomingdev.domain.usecase.HomeUseCase
import com.dionis.becomingdev.infrastructure.interfaces.usecase.IHomeUseCase
import com.dionis.becomingdev.infrastructure.interfaces.repository.HomeRepository
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