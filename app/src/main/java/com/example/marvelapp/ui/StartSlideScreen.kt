@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.example.marvelapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.marvelapp.data.HeroCardWithBack
import com.example.marvelapp.R
import com.example.marvelapp.data.HeroData
import com.example.marvelapp.navigation.Screens
import com.example.marvelapp.network.view.MarvelUiState
import com.example.marvelapp.network.view.MarvelViewModel
import com.example.marvelapp.ui.components.CardHeroUi
import com.example.marvelapp.ui.components.DrawCardBackground
import com.example.marvelapp.ui.components.HeroHeaderBlock
import com.example.marvelapp.ui.theme.MarvelAppTheme
import kotlinx.coroutines.launch

@Composable
fun StartSlideScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    marvelViewModel: MarvelViewModel,
) {
    when (marvelViewModel.marvelUiState) {

        is MarvelUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())

        is MarvelUiState.Success -> SlideScreen(
            modifier = modifier,
            navController = navController,
            marvelViewModel = marvelViewModel
        )

        is MarvelUiState.Error -> {
            ErrorScreen(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun SlideScreen(
    modifier: Modifier,
    navController: NavHostController,
    marvelViewModel: MarvelViewModel
) {
    val state = rememberLazyListState()

    val characters = marvelViewModel.characters.value!!
    val charactersNumber = characters.size

    val cards: List<HeroCardWithBack> by lazy {
        (0 until charactersNumber).map { i ->
            HeroCardWithBack(
                backgroundColor = HeroData.color[i],
                imageLink = "${characters[i].image.path}.${characters[i].image.extension}",
                name = characters[i].name
            )
        }
    }

    Box(modifier = modifier) {

        DrawCardBackground(
            color = cards[state.firstVisibleItemIndex].backgroundColor
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HeroHeaderBlock()

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
                itemsIndexed(cards) { index, card ->

                    val scope = rememberCoroutineScope()
                    val currentCharacter = characters[index]

                    CardHeroUi(
                        Modifier
                            .clip(RoundedCornerShape(dimensionResource(R.dimen.corner_slideCard)))
                            .size(
                                width = dimensionResource(R.dimen.size_slideCardWidth),
                                height = dimensionResource(R.dimen.size_slideCardHeight)
                            )
                            .clickable {

                                scope.launch {

                                    marvelViewModel.setCurrentCharacter(currentCharacter)
                                    navController.navigate(Screens.FullCard.route)
                                }
                            },
                        card
                    )

                }
            }

        }

    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(MarvelAppTheme.loadingScreen),
        contentAlignment = Alignment.Center
    ) {

        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(R.string.app_name),
                color = Color.White,
                style = MarvelAppTheme.TextStyle.ExtraBold_45
            )

            Spacer(Modifier.height(dimensionResource(R.dimen.spacer_textAndImage)))

            Image(
                modifier = Modifier.size(dimensionResource(R.dimen.size_loadingScreenImage)),
                painter = painterResource(R.drawable.loading_card),
                contentDescription = stringResource(R.string.loading)
            )
        }

    }

}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(
            text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(
                dimensionResource(R.dimen.padding_errorScreenText)
            )
        )
    }
}
