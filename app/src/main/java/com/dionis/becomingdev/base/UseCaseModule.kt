package com.dionis.becomingdev.base

import com.dionis.becomingdev.domain.usecase.HomeUseCase
import com.dionis.becomingdev.domain.usecase.ProfileUseCase
import com.dionis.becomingdev.infrastructure.interfaces.usecase.IHomeUseCase
import com.dionis.becomingdev.infrastructure.interfaces.repository.HomeRepository
import com.dionis.becomingdev.infrastructure.interfaces.repository.ProfileRepository
import com.dionis.becomingdev.infrastructure.interfaces.usecase.IProfileUseCase
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

    @Singleton
    @Provides
    fun ProvideProfileUseCase(profileRepository: ProfileRepository): IProfileUseCase {
        return ProfileUseCase(profileRepository)
    }

}