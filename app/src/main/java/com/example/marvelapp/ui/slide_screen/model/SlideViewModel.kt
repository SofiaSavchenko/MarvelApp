package com.example.marvelapp.ui.slide_screen.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.network.model.either.Either
import com.example.marvelapp.network.data.repository.Repository
import com.example.marvelapp.network.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SlideViewModel(private val repository: Repository) : ViewModel() {

    private val _uiState = MutableStateFlow(SlideUiState())
    val uiState: StateFlow<SlideUiState> = _uiState.asStateFlow()

    suspend fun observeData() {

        viewModelScope.launch {

            when (val result = repository.getMarvelCharacters()) {

                is Either.Success -> {

                    val cards = result.value

                    _uiState.update {
                        it.copy(
                            uiState = UiState.Success,
                            characterCards = cards
                        )
                    }
                }

                is Either.Fail -> {
                    _uiState.update { it.copy(uiState = UiState.Error) }
                }

            }

        }
    }

}