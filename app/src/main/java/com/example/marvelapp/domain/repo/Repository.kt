package com.example.marvelapp.domain.repo

import com.example.marvelapp.domain.model.CharacterUi
import com.example.marvelapp.data.remote.ApiError
import com.example.marvelapp.data.remote.model.either.Either

interface Repository {

    suspend fun getCharacters(): Either<ApiError, List<CharacterUi>>
    suspend fun getCharacterById(id: Int): Either<ApiError, CharacterUi>

}