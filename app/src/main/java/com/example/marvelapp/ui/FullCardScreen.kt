@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.marvelapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import com.example.marvelapp.objects.HeroCard
import com.example.marvelapp.R
import com.example.marvelapp.ui.components.CardHeroUi
import com.example.marvelapp.navigation.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FullCardScreen(
    modifier: Modifier,
    card: HeroCard,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = { navigateToStart(navController) },
                        modifier = Modifier
                            .size(dimensionResource(R.dimen.size_iconButton))
                            .padding(
                                top = dimensionResource(R.dimen.padding_iconButtonTop)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) {
        CardHeroUi(modifier = modifier, card = card)
    }

}

private fun navigateToStart(
    navController: NavHostController
) {
    navController.popBackStack(Screens.Start.route, inclusive = false)
}