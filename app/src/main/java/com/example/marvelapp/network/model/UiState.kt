package com.example.marvelapp.network.model

sealed interface UiState {

    data object Loading : UiState
    data object Success : UiState
    data object Error : UiState

}