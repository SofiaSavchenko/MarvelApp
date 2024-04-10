package com.example.marvelapp.ui.slide_screen.model

import com.example.marvelapp.data.HeroCardWithBack
import com.example.marvelapp.network.model.UiState

data class SlideUiState(

    val uiState : UiState = UiState.Loading,
    val characterCards: List<HeroCardWithBack> = emptyList()

)