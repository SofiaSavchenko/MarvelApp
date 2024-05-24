package com.example.marvelapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelapp.navigation.NavGraph

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    NavGraph(navController)

}
