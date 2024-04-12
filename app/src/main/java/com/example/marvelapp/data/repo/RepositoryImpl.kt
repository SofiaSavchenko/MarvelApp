package com.example.marvelapp.data.repo

import com.example.marvelapp.domain.model.CharacterUi
import com.example.marvelapp.data.local.model.CharacterEntity
import com.example.marvelapp.data.local.Dao
import com.example.marvelapp.data.mapper.toCharacterEntityFromDTO
import com.example.marvelapp.data.mapper.toCharacterListEntityFromDTO
import com.example.marvelapp.data.mapper.toCharacterListUiFromDTO
import com.example.marvelapp.data.mapper.toCharacterListUiFromEntity
import com.example.marvelapp.data.remote.ApiService
import com.example.marvelapp.data.remote.ApiError
import com.example.marvelapp.data.remote.model.either.Either
import com.example.marvelapp.data.mapper.toCharacterUiFromDTO
import com.example.marvelapp.data.mapper.toCharacterUiFromEntity
import com.example.marvelapp.domain.repo.Repository
import kotlinx.coroutines.flow.first


class RepositoryImpl(private val dao: Dao, private val api: ApiService) : Repository {

    override suspend fun getCharacters(): Either<ApiError, List<CharacterUi>> {

        val characterListEntity = dao.getAllCharacters().first()

        return if (characterListEntity.isNotEmpty()) {

            Either.success(characterListEntity.toCharacterListUiFromEntity())

        } else {

            when (val result = api.getCharacters()) {

                is Either.Success -> {

                    val characterListDTO = result.value.data.results

                    refreshRoomCache(characterListDTO.toCharacterListEntityFromDTO())

                    val characterListUi = characterListDTO.toCharacterListUiFromDTO()

                    Either.success(characterListUi)
                }

                is Either.Fail -> Either.fail(result.value)

            }
        }

    }

    override suspend fun getCharacterById(id: Int): Either<ApiError, CharacterUi> {

        val characterEntity = dao.getCharacterById(id).first()

        return if (characterEntity != null) {

            Either.success(characterEntity.toCharacterUiFromEntity())

        } else {

            when (val result = api.getCharacterById(id)) {

                is Either.Success -> {

                    val characterDTO = result.value.data.results.first()

                    dao.addCharacter(characterDTO.toCharacterEntityFromDTO())

                    val characterUi = characterDTO.toCharacterUiFromDTO()

                    Either.success(characterUi)
                }

                is Either.Fail -> Either.fail(result.value)
            }
        }

    }

    private suspend fun refreshRoomCache(characters: List<CharacterEntity>) {

        dao.deleteAllCharacters()
        dao.addAllCharacters(characters)
    }
}
