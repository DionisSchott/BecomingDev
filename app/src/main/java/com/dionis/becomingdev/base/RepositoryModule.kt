package com.dionis.becomingdev.base

import com.dionis.becomingdev.data.api.home.HomeApi
import com.dionis.becomingdev.data.api.login.UserApi
import com.dionis.becomingdev.data.api.newuser.NewUserApi
import com.dionis.becomingdev.data.api.photo.PostPhotoApi
import com.dionis.becomingdev.data.api.profile.ProfileApi
import com.dionis.becomingdev.data.api.registermember.RegisterMemberApi
import com.dionis.becomingdev.infrastructure.interfaces.repository.*
import com.dionis.becomingdev.infrastructure.repository.*
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
    fun providesProfileRepository(profileApi: ProfileApi): ProfileRepository {
        return ProfileRepositoryImpl(profileApi)
    }

    @Singleton
    @Provides
    fun providesRegisterMemberRepository(registerMemberApi: RegisterMemberApi): RegisterMemberRepository {
        return RegisterMemberRepositoryImpl(registerMemberApi)
    }

    @Singleton
    @Provides
    fun providesNewUserRepository(newUserApi: NewUserApi): NewUserRepository {
        return NewUserRepositoryImpl(newUserApi)
    }

    @Singleton
    @Provides
    fun providesLoginRepository(userApi: UserApi): LoginRepository {
        return LoginRepositoryImpl(userApi)
    }

    @Singleton
    @Provides
    fun providesPostPhotoRepository(postPhotoApi: PostPhotoApi): PostPhotoRepository {
        return PostPhotoRepositoryImpl(postPhotoApi)
    }


}
