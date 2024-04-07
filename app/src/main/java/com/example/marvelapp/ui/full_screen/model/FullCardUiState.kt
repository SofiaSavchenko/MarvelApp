package com.example.marvelapp.ui.full_screen.model

import com.example.marvelapp.data.HeroCardWithDesc
import com.example.marvelapp.network.model.UiState

data class FullCardUiState(

    val uiState : UiState = UiState.Loading,
    val characterCard: HeroCardWithDesc? = null

)
