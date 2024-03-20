package com.example.marvelapp.data

import androidx.compose.ui.graphics.Color

open class HeroCard(

    open val imageLink: String,
    open val name: String,

    )

data class HeroCardWithDesc(

    val description: String,
    override val imageLink: String,
    override val name: String,

    ) : HeroCard(imageLink, name)


data class HeroCardWithBack(

    val backgroundColor: Color,
    override val imageLink: String,
    override val name: String,

    ) : HeroCard(imageLink, name)
