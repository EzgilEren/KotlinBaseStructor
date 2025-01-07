package com.ezgieren.kotlinbasestructor.di

import android.content.Context
import androidx.room.Room
import com.ezgieren.kotlinbasestructor.data.local.dao.ExampleDao
import com.ezgieren.kotlinbasestructor.data.local.database.BaseDatabase
import com.ezgieren.kotlinbasestructor.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BaseDatabase {
        return Room.databaseBuilder(
            context,
            BaseDatabase::class.java,
            Constants.Database.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideExampleDao(database: BaseDatabase): ExampleDao {
        return database.exampleDao()
    }
}