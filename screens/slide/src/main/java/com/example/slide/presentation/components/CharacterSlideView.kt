package com.example.slide.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core_ui.models.CharacterUi
import com.example.core_ui.theme.MarvelAppTheme
import com.example.slide.R


@Composable
fun CharacterSlideView(
    modifier: Modifier = Modifier,
    card: CharacterUi,
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
        CompositionLocalProvider(value = LocalLayoutDirection provides LayoutDirection.Ltr) {

            val paddingStart = if (LocalLayoutDirection.current == LayoutDirection.Rtl) {
                0.dp
            } else {
                dimensionResource(R.dimen.padding_titleStart)
            }

            val paddingEnd = if (LocalLayoutDirection.current == LayoutDirection.Ltr) {
                dimensionResource(R.dimen.padding_titleStart)
            } else {
                0.dp
            }

            Text(
                text = card.name,
                modifier = Modifier
                    .padding(
                        start = paddingStart,
                        end = paddingEnd,
                        bottom = dimensionResource(R.dimen.padding_titleBottom)
                    )
                    .align(Alignment.BottomStart),
                style = MarvelAppTheme.TextStyle.ExtraBold_32
            )
        }

    }

}

@Preview
@Composable
fun PreviewCharacterSlideView() {

    MarvelAppTheme {

        Surface {

            CharacterSlideView(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(355.dp, 620.dp),

                card = CharacterUi(
                    id = 1,
                    name = "Deadpool",
                    description = "",
                    imageUrl = "https://iili.io/JMnAfIV.png",
                )
            )
        }
    }
}
