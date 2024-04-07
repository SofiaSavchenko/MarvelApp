package com.example.marvelapp.navigation

import com.example.marvelapp.ui.full_screen.model.FullCardViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.marvelapp.network.data.repository.Repository
import com.example.marvelapp.network.data.repository.RepositoryImpl
import com.example.marvelapp.ui.full_screen.FullCardScreen
import com.example.marvelapp.ui.slide_screen.model.SlideViewModel
import com.example.marvelapp.ui.slide_screen.StartSlideScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    repositoryImpl: Repository = RepositoryImpl()
) {
    val slideViewModel = SlideViewModel(repositoryImpl)
    val fullCardViewModel = FullCardViewModel(repositoryImpl)

    NavHost(
        navController = navController,
        startDestination = Screens.Start.route,
    ) {
        composable(route = Screens.Start.route) {

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
            FullCardScreen(
                id = cardId,
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                viewModel = fullCardViewModel
            )
        }
    }
}