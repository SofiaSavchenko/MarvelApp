package com.example.marvelapp.presentation.screens

sealed interface UiState {

    data object Loading : UiState
    data object Success : UiState
    data object Error : UiState

}