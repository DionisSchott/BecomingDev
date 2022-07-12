package com.dionis.auladokevyn.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor { chain: Interceptor.Chain? ->
                val original = chain!!.request()

                // Request customization: add request headers
                val response: Response

                val requestBuilder: Request.Builder = original.newBuilder()
                    .method(original.method, original.body)

                val request = requestBuilder.build()
                response = chain.proceed(request)

                return@addInterceptor response
            }
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl("https://pokeapi.co/api/v2/%22")
            .client(okHttpClient)
            .build()
    }
}