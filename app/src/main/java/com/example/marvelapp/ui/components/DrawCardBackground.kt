package com.example.marvelapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvelapp.data.HeroData
import com.example.marvelapp.ui.theme.MarvelAppTheme

@Composable
fun DrawCardBackground(color: Color) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MarvelAppTheme.backgroundScreen)
    )
    {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawPath(
                color = color,
                path = Path().apply {
                    moveTo(size.width, size.height * 0.4f)
                    lineTo(0f, size.height)
                    lineTo(size.width, size.height)
                    close()
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewDrawCardBackground() {
    DrawCardBackground(HeroData.backgroundColor[0])
}
