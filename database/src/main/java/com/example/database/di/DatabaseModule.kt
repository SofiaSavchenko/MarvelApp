package com.example.database.di

import android.content.Context
import com.example.database.AppDatabase
import com.example.database.Dao
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
    fun providesRoomDao(db: AppDatabase): Dao = db.dao()

    @Singleton
    @Provides
    fun providesRoomDb(@ApplicationContext appContext: Context): AppDatabase =
        AppDatabase.getDatabase(appContext)

}
