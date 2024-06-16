package com.example.core_ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.core_ui.R
import com.example.core_ui.theme.MarvelAppTheme

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