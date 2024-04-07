package com.example.marvelapp.network.data.repository

import com.example.marvelapp.data.HeroCardWithBack
import com.example.marvelapp.data.HeroCardWithDesc
import com.example.marvelapp.data.HeroData
import com.example.marvelapp.network.model.APIKeyInterceptor
import com.example.marvelapp.network.ApiService
import com.example.marvelapp.network.data.Constants
import com.example.marvelapp.network.model.ApiError
import com.example.marvelapp.network.model.either.Either
import com.example.marvelapp.network.model.either.EitherCallAdapterFactory
import com.example.marvelapp.network.model.mapper.toHeroCardWithDesc
import com.example.marvelapp.network.model.mapper.toHeroCardWithBack
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RepositoryImpl : Repository {

    private val url = Constants.BASE_URL

    private val marvelApi: ApiService

    init {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(APIKeyInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(EitherCallAdapterFactory())
            .client(okHttpClient)
            .build()

        marvelApi = retrofit.create(ApiService::class.java)
    }

    override suspend fun getMarvelCharacters(): Either<ApiError, List<HeroCardWithBack>> {

        return when (val result = marvelApi.getCharacters()) {

            is Either.Success -> {

                val characters = result.value.data.results

                val cards = characters.mapIndexed { index, character ->

                    val color = HeroData.color[index]
                    character.image.path =
                        character.image.path.replaceFirst("http://", "https://")
                    character.toHeroCardWithBack(color)
                }

                Either.success(cards)
            }

            is Either.Fail -> Either.fail(result.value)

        }
    }


    override suspend fun getMarvelCharacterById(id: Int): Either<ApiError, HeroCardWithDesc> {

        return when (val result = marvelApi.getCharacterById(id)) {

            is Either.Success -> {

                val character = result.value.data.results.map { character ->
                    character.image.path =
                        character.image.path.replaceFirst("http://", "https://")
                    character
                }.first()

                val card = character.toHeroCardWithDesc()
                Either.success(card)
            }

            is Either.Fail -> Either.fail(result.value)
        }

    }
}
