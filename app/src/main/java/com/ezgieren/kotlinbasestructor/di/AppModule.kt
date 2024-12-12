package com.ezgieren.kotlinbasestructor.di

import com.ezgieren.kotlinbasestructor.BuildConfig
import com.ezgieren.kotlinbasestructor.data.remote.api.ApiService
import com.ezgieren.kotlinbasestructor.data.repository.BaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideBaseRepository(apiService: ApiService): BaseRepository {
        return BaseRepository(apiService)
    }
}