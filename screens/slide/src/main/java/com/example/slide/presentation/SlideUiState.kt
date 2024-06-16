package com.example.slide.presentation

import com.example.core_ui.models.CharacterUi

sealed class SlideUiState {
    data object Loading : SlideUiState()
    data class Error(val error: Throwable) : SlideUiState()
    data class Success(val data: List<CharacterUi>) : SlideUiState()
}
