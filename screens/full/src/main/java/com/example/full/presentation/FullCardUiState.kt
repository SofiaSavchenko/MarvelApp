package com.example.full.presentation

import com.example.core_ui.models.CharacterUi

sealed class FullCardUiState {
    data object Loading : FullCardUiState()
    data class Error(val error: Throwable) : FullCardUiState()
    data class Success(val data: CharacterUi) : FullCardUiState()
}
