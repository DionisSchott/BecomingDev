package com.dionis.becomingdev.base

import com.dionis.becomingdev.data.api.home.HomeEndPoint
import com.dionis.becomingdev.data.api.registermember.RegisterMemberEndPoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class EndPointModule {

    @Singleton
    @Provides
    fun provideHomeEndPoint(retrofit: Retrofit): HomeEndPoint {
        return retrofit.create(HomeEndPoint::class.java)
    }

    @Singleton
    @Provides
    fun provideRegisterMemberEndPoint(retrofit: Retrofit): RegisterMemberEndPoint {
        return retrofit.create(RegisterMemberEndPoint::class.java)
    }


}