package com.example.marvelapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import com.example.marvelapp.HeroCard
import com.example.marvelapp.R

@Composable
fun FullCardScreen(
    modifier: Modifier,
    card: HeroCard,
    navController: NavHostController
) {
    Box(modifier = Modifier.fillMaxSize()) {

        CardHeroUi(modifier = modifier, card = card)

        IconButton(
            onClick = {  },
            modifier = Modifier
                .size(dimensionResource(R.dimen.size_iconButton))
                .padding(
                    start = dimensionResource(R.dimen.padding_iconButton),
                    top = dimensionResource(R.dimen.padding_iconButton)
                )
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}
