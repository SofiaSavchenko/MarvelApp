@file:OptIn(ExperimentalFoundationApi::class)

package com.example.slide.presentation

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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.core_ui.components.ErrorScreen
import com.example.core_ui.components.LoadingScreen
import com.example.core_ui.models.CharacterUi
import com.example.core.Screens
import com.example.core_ui.utils.isLandscape
import com.example.slide.R
import com.example.slide.presentation.components.DrawCardBackground
import com.example.slide.presentation.components.HeroHeaderBlock
import com.example.slide.presentation.components.CharacterSlideView
import com.example.slide.presentation.utils.CharacterColors
import com.example.slide.presentation.utils.DIVISION_FACTOR_LANDSCAPE
import com.example.slide.presentation.utils.DIVISION_FACTOR_PORTRAIT
import com.example.slide.presentation.utils.ITEM_RATIO_HEIGHT_LANDSCAPE
import com.example.slide.presentation.utils.ITEM_RATIO_HEIGHT_PORTRAIT
import com.example.slide.presentation.utils.ITEM_RATIO_WIDTH_LANDSCAPE
import com.example.slide.presentation.utils.ITEM_RATIO_WIDTH_PORTRAIT
import kotlinx.coroutines.launch

@Composable
fun StartSlideScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: SlideViewModel
) {

    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = uiState.value) {
        viewModel.observeData()
    }

    when (uiState.value) {

        is SlideUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())

        is SlideUiState.Success -> SlideScreen(
            modifier = modifier,
            navController = navController,
            characterCards = (uiState.value as SlideUiState.Success).data
        )

        is SlideUiState.Error -> {
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

    val maxWidth = LocalConfiguration.current.screenWidthDp
    val maxHeight = LocalConfiguration.current.screenHeightDp

    val itemRatioWidth =
        if (isLandscape()) ITEM_RATIO_WIDTH_LANDSCAPE else ITEM_RATIO_WIDTH_PORTRAIT
    val itemRatioHeight =
        if (isLandscape()) ITEM_RATIO_HEIGHT_LANDSCAPE else ITEM_RATIO_HEIGHT_PORTRAIT

    val division = if (isLandscape()) DIVISION_FACTOR_LANDSCAPE else DIVISION_FACTOR_PORTRAIT

    val itemWidth = (maxWidth * itemRatioWidth)
    val itemHeight = (maxHeight * itemRatioHeight)
    val padding = ((maxWidth / 2) - itemWidth / division).dp

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
                contentPadding = PaddingValues(start = padding, end = padding, bottom = padding / 4),
                horizontalArrangement = Arrangement.spacedBy(padding),
                state = state,
                flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
            ) {
                itemsIndexed(characterCards) { _, card ->

                    CharacterSlideView(
                        Modifier
                            .clip(RoundedCornerShape(dimensionResource(R.dimen.corner_slideCard)))
                            .size(
                                width = itemWidth.dp,
                                height = itemHeight.dp
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
