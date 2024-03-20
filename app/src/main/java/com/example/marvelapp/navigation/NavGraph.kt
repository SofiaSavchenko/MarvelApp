package com.example.marvelapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.marvelapp.ui.FullCardScreen
import com.example.marvelapp.network.view.MarvelViewModel
import com.example.marvelapp.ui.StartSlideScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {

    val marvelViewModel: MarvelViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screens.Start.route,
    ) {
        composable(route = Screens.Start.route) {
            StartSlideScreen(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                marvelViewModel = marvelViewModel
            )
        }

        composable(
            route = Screens.FullCard.route
        ) {
            FullCardScreen(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                marvelViewModel = marvelViewModel
            )
        }
    }

}