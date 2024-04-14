package com.example.marvelapp.presentation.screens.slide_screen.model

import com.example.marvelapp.domain.model.CharacterUi
sealed class SlideUiState {
    data object Loading : SlideUiState()
    data class Error(val error: Throwable) : SlideUiState()
    data class Success(val data: List<CharacterUi>) : SlideUiState()
}