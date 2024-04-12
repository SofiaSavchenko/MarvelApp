package com.example.marvelapp.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.marvelapp.presentation.screens.full_screen.FullCardScreen
import com.example.marvelapp.presentation.screens.full_screen.model.FullCardViewModel
import com.example.marvelapp.presentation.screens.slide_screen.model.SlideViewModel
import com.example.marvelapp.presentation.screens.slide_screen.StartSlideScreen
import com.example.marvelapp.presentation.screens.Screens

@Composable
fun NavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screens.Start.route,
    ) {
        composable(route = Screens.Start.route) {

            val slideViewModel: SlideViewModel = hiltViewModel()

            StartSlideScreen(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                viewModel = slideViewModel
            )
        }

        composable(
            route = "${Screens.FullCard.route}/{cardId}"

        ) { backStackEntry ->

            val cardId = backStackEntry.arguments?.getString("cardId")?.toInt()!!
            val fullCardViewModel: FullCardViewModel = hiltViewModel()

            FullCardScreen(
                id = cardId,
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                viewModel = fullCardViewModel
            )
        }
    }
}