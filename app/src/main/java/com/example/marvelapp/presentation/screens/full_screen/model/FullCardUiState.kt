package com.example.marvelapp.presentation.screens.full_screen.model

import com.example.marvelapp.domain.model.CharacterUi
import com.example.marvelapp.presentation.screens.UiState

data class FullCardUiState(

    val uiState : UiState = UiState.Loading,
    val characterCard: CharacterUi? = null

)
