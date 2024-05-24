package com.example.network.data

import com.example.core_ui.models.CharacterUi
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCharacters(): Flow<List<CharacterUi>>
    suspend fun getCharacterById(id: Int): Flow<CharacterUi>

}