package com.example.marvelapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.marvelapp.R
import com.example.marvelapp.ui.theme.MarvelAppTheme

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
            color = Color.White,
            style = MarvelAppTheme.TextStyle.ExtraBold_28
        )
    }
}

@Composable
fun LoadLogoImage() {

    AsyncImage(
        model = "https://effectiveband.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fab5510e9-0d2f-404e-9f55-374c7a36d382%2F014c0cb6-64d9-45bd-a3e1-a3cf608257e3%2FUntitled.png?table=block&id=47c3f47d-d604-431d-aa05-6728c63df83d&spaceId=ab5510e9-0d2f-404e-9f55-374c7a36d382&width=2000&userId=&cache=v2",
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

        Surface(color = MarvelAppTheme.backgroundScreen) {

            HeroHeaderBlock()
        }
    }

}
