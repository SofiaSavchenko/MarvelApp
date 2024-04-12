package com.example.marvelapp.presentation.screens.slide_screen.model

import com.example.marvelapp.domain.model.CharacterUi
import com.example.marvelapp.presentation.screens.UiState

data class SlideUiState(

    val uiState : UiState = UiState.Loading,
    val characterCards: List<CharacterUi> = emptyList()

)