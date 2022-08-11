package com.dionis.becomingdev.base

import com.dionis.becomingdev.data.api.home.HomeApi
import com.dionis.becomingdev.data.api.registermember.RegisterMemberApi
import com.dionis.becomingdev.domain.registerMember.RegisterMemberRepository
import com.dionis.becomingdev.domain.registerMember.RegisterMemberRepositoryImpl
import com.dionis.becomingdev.infrastructure.interfaces.repository.HomeRepository
import com.dionis.becomingdev.infrastructure.repository.HomeRepositoryImpl
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

    @Singleton
    @Provides
    fun providesRegisterMemberRepository(registerMemberApi: RegisterMemberApi): RegisterMemberRepository {
        return RegisterMemberRepositoryImpl(registerMemberApi)
    }

}
