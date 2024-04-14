@file:OptIn(
    ExperimentalMaterial3Api::class
)

package com.example.marvelapp.presentation.screens.full_screen

import com.example.marvelapp.presentation.screens.full_screen.model.FullCardViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import com.example.marvelapp.R
import com.example.marvelapp.domain.model.CharacterUi
import com.example.marvelapp.presentation.screens.Screens
import com.example.marvelapp.presentation.screens.components.ErrorScreen
import com.example.marvelapp.presentation.screens.components.LoadingScreen
import com.example.marvelapp.presentation.screens.full_screen.components.CharacterView
import com.example.marvelapp.presentation.screens.full_screen.model.FullCardUiState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun FullCardScreen(
    id: Int,
    modifier: Modifier,
    navController: NavHostController,
    viewModel: FullCardViewModel
) {

    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = uiState.value) {

        viewModel.observeDataById(id)

    }

    when (uiState.value) {

        is FullCardUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is FullCardUiState.Success -> {
            FullScreen(
                modifier = modifier,
                navController = navController,
                characterCard = (uiState.value as FullCardUiState.Success).data
            )
        }

        is FullCardUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FullScreen(
    modifier: Modifier,
    navController: NavHostController,
    characterCard: CharacterUi
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

        CharacterView(
            modifier = modifier,
            card = characterCard
        )

    }
}

private fun navigateToStart(
    navController: NavHostController
) {
    navController.popBackStack(Screens.Start.route, inclusive = false)
}
