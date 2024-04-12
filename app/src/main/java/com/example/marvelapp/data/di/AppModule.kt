package com.example.marvelapp.data.di

import android.content.Context
import com.example.marvelapp.data.local.AppDatabase
import com.example.marvelapp.data.local.Dao
import com.example.marvelapp.data.remote.ApiService
import com.example.marvelapp.data.remote.ApiConstants
import com.example.marvelapp.domain.repo.Repository
import com.example.marvelapp.data.repo.RepositoryImpl
import com.example.marvelapp.data.remote.model.APIKeyInterceptor
import com.example.marvelapp.data.remote.model.either.EitherCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val url = ApiConstants.BASE_URL

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .addNetworkInterceptor(APIKeyInterceptor())
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(EitherCallAdapterFactory())
            .client(client)
            .build()
    }

    @Provides
    fun providesRetrofitApi(retrofit: Retrofit): ApiService {

        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun providesRoomDao(db: AppDatabase): Dao {

        return db.dao()
    }

    @Singleton
    @Provides
    fun providesRoomDb(@ApplicationContext appContext: Context): AppDatabase {

        return AppDatabase.getDatabase(appContext)
    }

    @Provides
    @Singleton
    fun providesRepo(db: AppDatabase, api: ApiService): Repository {

        return RepositoryImpl(db.dao(), api)
    }

}
