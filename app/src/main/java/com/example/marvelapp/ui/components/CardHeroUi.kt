package com.example.marvelapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.marvelapp.data.HeroCard
import com.example.marvelapp.data.HeroCardWithDesc
import com.example.marvelapp.R
import com.example.marvelapp.ui.theme.MarvelAppTheme


@Composable
fun CardHeroUi(
    modifier: Modifier = Modifier,
    card: HeroCard,
) {

    Box(
        modifier = modifier
    ) {

        AsyncImage(
            model = card.imageLink,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        when (card) {

            is HeroCardWithDesc -> {

                Column(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(R.dimen.padding_titleWithDescStart),
                            bottom = dimensionResource(R.dimen.padding_titleWithDescBottom)
                        )
                        .align(Alignment.BottomStart)
                ) {

                    Text(
                        text = card.name,
                        color = Color.White,
                        style = MarvelAppTheme.TextStyle.ExtraBold_34
                    )

                    Spacer(Modifier.height(dimensionResource(R.dimen.spacer_titleAndDescription)))

                    Text(
                        text = card.description,
                        modifier = Modifier
                            .size(
                                height = dimensionResource(R.dimen.size_descriptionHeight),
                                width = dimensionResource(R.dimen.size_descriptionWidth)
                            )
                            .alpha(0.9f),
                        color = Color.White,
                        style = MarvelAppTheme.TextStyle.Bold_22
                    )

                }

            }

            else -> {

                Text(
                    text = card.name,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(R.dimen.padding_titleStart),
                            bottom = dimensionResource(R.dimen.padding_titleBottom)
                        )
                        .align(Alignment.BottomStart),
                    color = Color.White,
                    style = MarvelAppTheme.TextStyle.ExtraBold_32
                )

            }
        }

    }

}

@Preview
@Composable
fun PreviewCardHeroUiStartScreen() {

    MarvelAppTheme {

        Surface {

            CardHeroUi(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(355.dp, 620.dp),

                card = HeroCard(
                    imageLink = "https://iili.io/JMnAfIV.png",
                    name = "Deadpool"
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewCardHeroUiFullScreen() {

    MarvelAppTheme {

        Surface {

            CardHeroUi(
                modifier = Modifier
                    .size(355.dp, 620.dp),

                card = HeroCardWithDesc(
                    imageLink = "https://iili.io/JMnAfIV.png",
                    name = "Deadpool",
                    description = "Please don’t make the super suit green...or animated!"
                )
            )
        }
    }
}
