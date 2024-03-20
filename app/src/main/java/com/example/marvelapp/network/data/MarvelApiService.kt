package com.example.marvelapp.network.data

import com.example.marvelapp.network.model.CharacterDataWrapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val url = Constants.BASE_URL

private val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(url)
    .client(okHttpClient)
    .build()

interface MarvelApiService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("limit") limit: Int = Constants.limit

    ): CharacterDataWrapper

}

object MarvelApi {
    val retrofitService: MarvelApiService by lazy {
        retrofit.create(MarvelApiService::class.java)
    }
}
