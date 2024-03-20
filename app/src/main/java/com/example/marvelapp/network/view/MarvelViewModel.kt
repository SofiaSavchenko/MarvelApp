package com.example.marvelapp.network.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.network.model.Character
import com.example.marvelapp.network.data.MarvelApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MarvelUiState {
    data class Success(val characters: List<Character>) : MarvelUiState
    data object Error : MarvelUiState
    data object Loading : MarvelUiState
}

class MarvelViewModel : ViewModel() {

    var marvelUiState: MarvelUiState by mutableStateOf(MarvelUiState.Loading)
        private set

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    init {
        getMarvelCharacters()
    }

    private fun getMarvelCharacters() {
        viewModelScope.launch {
            marvelUiState = MarvelUiState.Loading
            try {

                val characters =
                    MarvelApi.retrofitService.getCharacters().data.results.map { character ->
                        character.image.path =
                            character.image.path.replaceFirst("http://", "https://")
                        character
                    }

                _characters.value = characters

                marvelUiState = MarvelUiState.Success(characters)

            } catch (e: IOException) {
                MarvelUiState.Error

            } catch (e: HttpException) {
                MarvelUiState.Error

            }
        }
    }

    fun setCurrentCharacter(character: Character) {

        _character.value = character

    }
}
