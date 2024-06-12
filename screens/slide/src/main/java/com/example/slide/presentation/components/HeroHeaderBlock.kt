package com.example.slide.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.core_ui.theme.MarvelAppTheme
import com.example.slide.R

@Composable
fun HeroHeaderBlock() {

    Column(
        modifier = Modifier.padding(
            top = dimensionResource(R.dimen.padding_headerTop),
            bottom = dimensionResource(R.dimen.padding_headerBottom)
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LoadLogoImage()

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer_header)))

        Text(
            text = stringResource(R.string.header_title),
            style = MarvelAppTheme.TextStyle.ExtraBold_28
        )
    }
}

@Composable
private fun LoadLogoImage() {

    AsyncImage(
        model = "https://iili.io/JMnuvbp.png",
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .width(dimensionResource(R.dimen.size_logoImageWidth))
            .height(dimensionResource(R.dimen.size_logoImageHeight))
    )
}

@Preview
@Composable
fun PreviewHeroHeader() {
    MarvelAppTheme {

        Surface(color = MaterialTheme.colorScheme.secondary) {

            HeroHeaderBlock()
        }
    }

}
