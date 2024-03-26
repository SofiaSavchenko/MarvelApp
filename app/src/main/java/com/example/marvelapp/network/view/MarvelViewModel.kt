package com.example.marvelapp.network.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.marvelapp.data.HeroCardWithBack
import com.example.marvelapp.data.HeroData
import com.example.marvelapp.network.model.Character
import com.example.marvelapp.network.data.MarvelApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface MarvelUiState {
    data class Success(val characters: List<HeroCardWithBack>) : MarvelUiState
    data object Error : MarvelUiState
    data object Loading : MarvelUiState
}

class MarvelViewModel : ViewModel() {

    var marvelUiState: MarvelUiState by mutableStateOf(MarvelUiState.Loading)
        private set

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    private val _character = MutableStateFlow<Character?>(null)
    val character: StateFlow<Character?> = _character

    private lateinit var characterCards: List<HeroCardWithBack>

    init {
        viewModelScope.launch {
            marvelUiState = MarvelUiState.Loading

            when (val result = getMarvelCharacters()) {

                is Either.Right -> {

                    val characters = result.value

                    _characters.value = characters

                    val cards = transformCharacterToHeroCardWithBack(characters)
                    characterCards = cards

                    marvelUiState = MarvelUiState.Success(cards)
                }

                is Either.Left -> marvelUiState = MarvelUiState.Error

            }

        }
    }

    private suspend fun getMarvelCharacters(): Either<Throwable, List<Character>> = try {

        val characters =
            MarvelApi.retrofitService.getCharacters().data.results.map { character ->
                character.image.path = character.image.path.replaceFirst("http://", "https://")
                character
            }

        characters.right()

    } catch (e: Throwable) {
        e.left()
    }

    private fun transformCharacterToHeroCardWithBack(characters: List<Character>): List<HeroCardWithBack> {

        val cards: List<HeroCardWithBack> by lazy(characters) {
            characters.mapIndexed { i, character ->
                HeroCardWithBack(
                    backgroundColor = HeroData.color[i],
                    imageLink = "${character.image.path}.${character.image.extension}",
                    name = character.name
                )
            }
        }

        return cards

    }

    fun getCharacterCards(): List<HeroCardWithBack> {

        return characterCards

    }

    fun setCurrentCharacter(character: Character) {

        _character.value = character

    }
}
