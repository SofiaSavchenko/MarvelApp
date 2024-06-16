package com.example.core

sealed class Screens(val route: String) {
    data object Start : Screens("root_screen")
    data object FullCard : Screens("card_screen")
}
