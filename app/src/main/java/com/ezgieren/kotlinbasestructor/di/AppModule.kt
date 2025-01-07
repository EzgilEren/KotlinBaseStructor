package com.ezgieren.kotlinbasestructor.di

import com.ezgieren.kotlinbasestructor.data.local.dao.ExampleDao
import com.ezgieren.kotlinbasestructor.data.remote.api.ExampleApiService
import com.ezgieren.kotlinbasestructor.data.remote.api.PostApiService
import com.ezgieren.kotlinbasestructor.data.repository.ExampleRepositoryImpl
import com.ezgieren.kotlinbasestructor.domain.repository.ExampleRepository
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
            .baseUrl("https://jsonplaceholder.typicode.com/") // Örnek URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideExampleApiService(retrofit: Retrofit): ExampleApiService {
        return retrofit.create(ExampleApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePostApiService(retrofit: Retrofit): PostApiService {
        return retrofit.create(PostApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideExampleRepository(
        apiService: ExampleApiService,
        exampleDao: ExampleDao
    ): ExampleRepository {
        return ExampleRepositoryImpl(apiService, exampleDao)
    }
}