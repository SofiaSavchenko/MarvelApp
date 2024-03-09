package com.example.marvelapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelapp.ui.FullCardScreen
import com.example.marvelapp.ui.StartSlideScreen

object Screens {

    val startScreen = R.string.startScreen.toString()
    val fullScreen = R.string.fullScreen.toString()
}

@Composable
fun MarvelApp(
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = Screens.startScreen,
    ) {
        composable(route = Screens.startScreen) {
            StartSlideScreen(
                modifier = Modifier.fillMaxSize(),
                cards = HeroCardsWithBack.getHeroCards(),
                navController = navController
            )
        }

        composable(
            route = "${Screens.fullScreen}/{cardId}"
        ) { backStackEntry ->
            val cardId = backStackEntry.arguments?.getString("cardId")?.toIntOrNull()
            val card = cardId?.let { HeroCardsWithDesc.getHeroCards()[it] }
            FullCardScreen(modifier = Modifier.fillMaxSize(), card = card!!, navController = navController)
        }
    }

}
