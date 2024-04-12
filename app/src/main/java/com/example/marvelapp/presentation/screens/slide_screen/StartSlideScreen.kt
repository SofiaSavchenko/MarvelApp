@file:OptIn(ExperimentalFoundationApi::class)

package com.example.marvelapp.presentation.screens.slide_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import com.example.marvelapp.R
import com.example.marvelapp.domain.model.CharacterUi
import com.example.marvelapp.presentation.util.CharacterColors
import com.example.marvelapp.presentation.screens.Screens
import com.example.marvelapp.presentation.screens.UiState
import com.example.marvelapp.presentation.screens.slide_screen.components.DrawCardBackground
import com.example.marvelapp.presentation.screens.components.ErrorScreen
import com.example.marvelapp.presentation.screens.slide_screen.components.HeroHeaderBlock
import com.example.marvelapp.presentation.screens.components.LoadingScreen
import com.example.marvelapp.presentation.screens.slide_screen.components.CharacterSlideView
import com.example.marvelapp.presentation.screens.slide_screen.model.SlideViewModel
import kotlinx.coroutines.launch

@Composable
fun StartSlideScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: SlideViewModel
) {

    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = uiState.value){
        viewModel.observeData()
    }

    when (uiState.value.uiState) {

        is UiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())

        is UiState.Success -> SlideScreen(
            modifier = modifier,
            navController = navController,
            characterCards = uiState.value.characterCards
        )

        is UiState.Error -> {
            ErrorScreen(modifier = Modifier.fillMaxSize())
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SlideScreen(
    modifier: Modifier,
    navController: NavHostController,
    characterCards: List<CharacterUi>
) {
    val state = rememberLazyListState()

    Box(modifier = modifier) {

        DrawCardBackground(
            color = CharacterColors.color[state.firstVisibleItemIndex]
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HeroHeaderBlock()

            val scope = rememberCoroutineScope()

            LazyRow(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = dimensionResource(R.dimen.padding_slideCardsStart),
                    end = dimensionResource(R.dimen.padding_slideCardsEnd),
                    bottom = dimensionResource(R.dimen.padding_slideCardsBottom)
                ),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_slideCardsSpaced)),
                state = state,
                flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
            ) {
                itemsIndexed(characterCards) { _, card ->

                    CharacterSlideView(
                        Modifier
                            .clip(RoundedCornerShape(dimensionResource(R.dimen.corner_slideCard)))
                            .size(
                                width = dimensionResource(R.dimen.size_slideCardWidth),
                                height = dimensionResource(R.dimen.size_slideCardHeight)
                            )
                            .clickable {

                                scope.launch {

                                    navController.navigate("${Screens.FullCard.route}/${card.id}")
                                }
                            },
                        card
                    )

                }
            }

        }

    }
}
