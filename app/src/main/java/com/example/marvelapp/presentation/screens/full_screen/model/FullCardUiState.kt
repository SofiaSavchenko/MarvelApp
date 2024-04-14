package com.example.marvelapp.presentation.screens.full_screen.model

import com.example.marvelapp.domain.model.CharacterUi

sealed class FullCardUiState {
    data object Loading : FullCardUiState()
    data class Error(val error: Throwable) : FullCardUiState()
    data class Success(val data: CharacterUi) : FullCardUiState()
}
