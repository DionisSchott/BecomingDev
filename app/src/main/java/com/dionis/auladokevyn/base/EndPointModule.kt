package com.dionis.auladokevyn.base

import com.dionis.auladokevyn.data.api.HomeEndPoint
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
        fun provideHomeEndPoint(retrofit: Retrofit): HomeEndPoint{
            return retrofit.create(HomeEndPoint::class.java)
        }

}