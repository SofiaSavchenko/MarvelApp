package com.example.marvelapp.data.repo

import com.example.marvelapp.domain.model.CharacterUi
import com.example.marvelapp.data.local.model.CharacterEntity
import com.example.marvelapp.data.local.Dao
import com.example.marvelapp.data.mapper.toCharacterEntityFromDTO
import com.example.marvelapp.data.mapper.toCharacterListEntityFromDTO
import com.example.marvelapp.data.mapper.toCharacterListUiFromDTO
import com.example.marvelapp.data.mapper.toCharacterListUiFromEntity
import com.example.marvelapp.data.remote.ApiService
import com.example.marvelapp.data.remote.model.either.Either
import com.example.marvelapp.data.mapper.toCharacterUiFromDTO
import com.example.marvelapp.data.mapper.toCharacterUiFromEntity
import com.example.marvelapp.domain.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RepositoryImpl(private val dao: Dao, private val api: ApiService) : Repository {

    override suspend fun getCharacters(): Flow<List<CharacterUi>> = flow {

        val characterListEntity = dao.getAllCharacters().first()

        if (characterListEntity.isNotEmpty()) {

            emit(characterListEntity.toCharacterListUiFromEntity())

        } else {

            when (val result = api.getCharacters()) {

                is Either.Success -> {

                    val characterListDTO = result.value.data.results

                    refreshRoomCache(characterListDTO.toCharacterListEntityFromDTO())

                    val characterListUi = characterListDTO.toCharacterListUiFromDTO()

                    emit(characterListUi)
                }

                is Either.Fail -> throw Throwable(result.value.toString())

            }
        }

    }.flowOn(Dispatchers.IO)
        .catch { error -> throw error }


    override suspend fun getCharacterById(id: Int): Flow<CharacterUi> = flow {

        val characterEntity = dao.getCharacterById(id).first()

        if (characterEntity != null) {

            emit(characterEntity.toCharacterUiFromEntity())

        } else {

            when (val result = api.getCharacterById(id)) {

                is Either.Success -> {

                    val characterDTO = result.value.data.results.first()

                    dao.addCharacter(characterDTO.toCharacterEntityFromDTO())

                    val characterUi = characterDTO.toCharacterUiFromDTO()

                    emit(characterUi)
                }

                is Either.Fail -> throw Throwable(result.value.toString())
            }
        }

    }.flowOn(Dispatchers.IO)
        .catch { error -> throw error }

    private suspend fun refreshRoomCache(characters: List<CharacterEntity>) {

        dao.deleteAllCharacters()
        dao.addAllCharacters(characters)
    }
}
