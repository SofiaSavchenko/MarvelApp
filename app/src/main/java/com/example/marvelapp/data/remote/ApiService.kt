package com.example.marvelapp.data.remote

import com.example.marvelapp.data.remote.model.either.Either
import com.example.marvelapp.data.remote.model.CharacterDataWrapperDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") limit: String = ApiConstants.LIMIT
    ): Either<ApiError, CharacterDataWrapperDTO>

    @GET("characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") id: Int
    ): Either<ApiError, CharacterDataWrapperDTO>
}
