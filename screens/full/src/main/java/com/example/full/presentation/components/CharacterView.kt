package com.example.full.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core_ui.models.CharacterUi
import com.example.core_ui.theme.MarvelAppTheme
import com.example.full.R


@Composable
fun CharacterView(
    modifier: Modifier = Modifier,
    card: CharacterUi
) {

    Box(
        modifier = modifier
    ) {

        AsyncImage(
            model = card.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        Column(
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.padding_titleWithDescStart),
                    bottom = dimensionResource(R.dimen.padding_titleWithDescBottom)
                )
                .align(Alignment.BottomStart)
        ) {

            CompositionLocalProvider(value = LocalLayoutDirection provides LayoutDirection.Ltr) {
                Text(
                    text = card.name,
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MarvelAppTheme.TextStyle.ExtraBold_34
                )
            }

            Spacer(Modifier.height(dimensionResource(R.dimen.spacer_titleAndDescription)))

            Text(
                text = card.description,
                modifier = Modifier.alpha(0.9f),
                color = MaterialTheme.colorScheme.onSecondary,
                style = MarvelAppTheme.TextStyle.Bold_22
            )
        }

    }

}


@Preview
@Composable
fun PreviewCardHeroUiFullScreen() {

    MarvelAppTheme {

        Surface {

            CharacterView(
                modifier = Modifier
                    .size(355.dp, 620.dp),

                card = CharacterUi(
                    id = 1,
                    name = "Deadpool",
                    description = "Please don’t make the super suit green...or animated!",
                    imageUrl = "https://iili.io/JMnAfIV.png"
                )
            )
        }
    }
}
