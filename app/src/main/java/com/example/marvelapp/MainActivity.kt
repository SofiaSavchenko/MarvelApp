package com.example.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.example.marvelapp.ui.theme.MarvelAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MarvelAppTheme {

                ApplySystemBarColors()

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MarvelApp()
                }
            }
        }
    }
}

@Composable
private fun ApplySystemBarColors() {

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Transparent)
        systemUiController.setNavigationBarColor(color = Color.Transparent)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MarvelAppTheme {

        ApplySystemBarColors()

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MarvelApp()
        }
    }
}