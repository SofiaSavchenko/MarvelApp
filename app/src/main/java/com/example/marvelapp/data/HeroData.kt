package com.example.marvelapp.data

import androidx.compose.ui.graphics.Color
import com.example.marvelapp.R

object HeroData {

    val image = listOf(

        "https://iili.io/JMnAfIV.png",
        "https://iili.io/JMnuDI2.png",
        "https://iili.io/JMnuyB9.png"
    )

    val name = listOf(
        R.string.deadpool,
        R.string.iron_man,
        R.string.spider_man
    )

    val description = listOf(
        R.string.description_deadpool,
        R.string.description_iron_man,
        R.string.description_spider_man
    )

    val color = listOf(
        Color(0xFF991518),
        Color(0xFFCB9E48),
        Color(0xFF2F3A9A),
    )

}