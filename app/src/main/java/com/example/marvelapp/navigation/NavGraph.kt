package com.example.marvelapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.marvelapp.HeroCardsWithBack
import com.example.marvelapp.HeroCardsWithDesc
import com.example.marvelapp.ui.FullCardScreen
import com.example.marvelapp.ui.StartSlideScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screens.Start.route,
    ) {
        composable(route = Screens.Start.route) {
            StartSlideScreen(
                modifier = Modifier.fillMaxSize(),
                cards = HeroCardsWithBack.getHeroCards(),
                navController = navController
            )
        }

        composable(
            route = "${Screens.FullCard.route}/{cardId}"
        ) { backStackEntry ->
            val cardId = backStackEntry.arguments?.getString("cardId")?.toIntOrNull()
            val card = cardId?.let { HeroCardsWithDesc.getHeroCards()[it] }
            FullCardScreen(
                modifier = Modifier.fillMaxSize(),
                card = card!!,
                navController = navController
            )
        }
    }

}