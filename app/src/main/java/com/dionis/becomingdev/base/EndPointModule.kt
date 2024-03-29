package com.dionis.becomingdev.base

import com.dionis.becomingdev.data.api.home.HomeEndPoint
import com.dionis.becomingdev.data.api.login.UserEndPoint
import com.dionis.becomingdev.data.api.newuser.NewUserEndPoint
import com.dionis.becomingdev.data.api.photo.PostPhotoEndPoint
import com.dionis.becomingdev.data.api.profile.ProfileEndPoint
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
    fun provideProfileEndPoint(retrofit: Retrofit): ProfileEndPoint {
        return retrofit.create(ProfileEndPoint::class.java)
    }

    @Singleton
    @Provides
    fun provideRegisterMemberEndPoint(retrofit: Retrofit): RegisterMemberEndPoint {
        return retrofit.create(RegisterMemberEndPoint::class.java)
    }

    @Singleton
    @Provides
    fun provideNewUserEndPoint(retrofit: Retrofit): NewUserEndPoint {
        return retrofit.create(NewUserEndPoint::class.java)
    }

    @Singleton
    @Provides
    fun provideUserPoint(retrofit: Retrofit): UserEndPoint {
        return retrofit.create(UserEndPoint::class.java)
    }

    @Singleton
    @Provides
    fun providePostPhotoEndPoint(retrofit: Retrofit): PostPhotoEndPoint {
        return retrofit.create(PostPhotoEndPoint::class.java)
    }


}