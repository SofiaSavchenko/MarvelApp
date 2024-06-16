package com.example.marvelapp.data.di

import com.example.database.AppDatabase
import com.example.network.data.Repository
import com.example.network.data.RepositoryImpl
import com.example.network.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesRepo(db: AppDatabase, api: ApiService): Repository =
        RepositoryImpl(db.dao(), api)

}
