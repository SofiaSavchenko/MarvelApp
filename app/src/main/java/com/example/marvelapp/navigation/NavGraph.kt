package com.example.marvelapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.core.Screens
import com.example.full.presentation.FullCardScreen
import com.example.full.presentation.FullCardViewModel
import com.example.marvelapp.utils.NotificationConstants
import com.example.slide.presentation.SlideViewModel
import com.example.slide.presentation.StartSlideScreen

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
            route = "${Screens.FullCard.route}/{cardId}",
            deepLinks = listOf(
                navDeepLink { uriPattern = "${NotificationConstants.URI}/{cardId}" }
            )

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
