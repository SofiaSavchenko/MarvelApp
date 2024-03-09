@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.example.marvelapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.marvelapp.HeroCardWithBack
import com.example.marvelapp.R

@Composable
fun StartSlideScreen(
    modifier: Modifier = Modifier,
    cards: List<HeroCardWithBack>,
    navController: NavHostController,
) {
    val state = rememberLazyListState()

    Box(modifier = modifier) {

        Image(
            painter = painterResource(cards[state.firstVisibleItemIndex].backgroundResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
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

                    CardHeroUi(
                        Modifier
                            .clip(RoundedCornerShape(dimensionResource(R.dimen.corner_slideCard)))
                            .size(
                                width = dimensionResource(R.dimen.size_slideCardWidth),
                                height = dimensionResource(R.dimen.size_slideCardHeight)
                            )
                            .clickable {

                            },
                        card
                    )

                }
            }

        }

    }
}

