package com.dionis.becomingdev.base

import com.dionis.becomingdev.data.api.home.HomeApi
import com.dionis.becomingdev.data.api.login.UserApi
import com.dionis.becomingdev.data.api.newuser.NewUserApi
import com.dionis.becomingdev.data.api.registermember.RegisterMemberApi
import com.dionis.becomingdev.infrastructure.interfaces.repository.RegisterMemberRepository
import com.dionis.becomingdev.infrastructure.repository.RegisterMemberRepositoryImpl
import com.dionis.becomingdev.infrastructure.interfaces.repository.HomeRepository
import com.dionis.becomingdev.infrastructure.interfaces.repository.LoginRepository
import com.dionis.becomingdev.infrastructure.interfaces.repository.NewUserRepository
import com.dionis.becomingdev.infrastructure.repository.HomeRepositoryImpl
import com.dionis.becomingdev.infrastructure.repository.LoginRepositoryImpl
import com.dionis.becomingdev.infrastructure.repository.NewUserRepositoryImpl
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

}
