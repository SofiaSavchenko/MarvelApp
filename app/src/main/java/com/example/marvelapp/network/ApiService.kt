package com.example.marvelapp.network

import com.example.marvelapp.network.data.Constants
import com.example.marvelapp.network.model.CharacterDataContainerDTO
import com.example.marvelapp.network.model.either.Either
import com.example.marvelapp.network.model.ApiError
import com.example.marvelapp.network.model.CharacterDataWrapperDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") limit: String = Constants.LIMIT
    ): Either<ApiError, CharacterDataWrapperDTO>

    @GET("characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") id: Int
    ): Either<ApiError, CharacterDataWrapperDTO>
}
