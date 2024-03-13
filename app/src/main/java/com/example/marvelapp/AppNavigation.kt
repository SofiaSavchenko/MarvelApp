package com.example.marvelapp

import androidx.compose.runtime.Composable
import com.example.marvelapp.navigation.NavGraph
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    NavGraph(navController)

}
