package com.example.marvelapp.domain.repo

import com.example.marvelapp.domain.model.CharacterUi
import com.example.marvelapp.data.remote.ApiError
import com.example.marvelapp.data.remote.model.either.Either
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCharacters(): Flow<List<CharacterUi>>
    suspend fun getCharacterById(id: Int): Flow<CharacterUi>

}