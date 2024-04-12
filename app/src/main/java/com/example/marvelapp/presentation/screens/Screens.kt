package com.example.marvelapp.presentation.screens

sealed class Screens(val route: String) {
    data object Start: Screens("start_screen")
    data object FullCard: Screens("fullCard_screen")
}
