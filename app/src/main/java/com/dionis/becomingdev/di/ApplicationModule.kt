package com.dionis.becomingdev.di

import android.content.Context
import com.dionis.becomingdev.data.storage.SessionManager
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
                val response: Response
                val requestBuilder: Request.Builder
                val token = SessionManager(context).getToken()
//                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiZW1haWwiOiJ2aWN0b3IudnFzQGhvdG1haWwuY29tIiwiaWF0IjoxNjU5NjU3ODY0LCJleHAiOjE2NjAyNjI2NjR9.pcdg6IdjRoo_fFPYt84CccC1E_SvkyV1ilgW12nRXGY"


                requestBuilder = if (!token.isNullOrEmpty()) {
                    original.newBuilder()
                        .header("Authorization", "Token $token")
                        .method(original.method, original.body)
                } else {
                    original.newBuilder()
                        .method(original.method, original.body)
                }

                val request = requestBuilder.build()
                response = chain.proceed(request)

                return@addInterceptor response
            }
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl("https://becomingdevapi.victorhqdev.ninja/")
            .client(okHttpClient)
            .build()
    }
}