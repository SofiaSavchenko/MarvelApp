package com.example.marvelapp.data

import androidx.compose.ui.graphics.Color

open class HeroCard(

    open val id: Int,
    open val imageLink: String,
    open val name: String
)

data class HeroCardWithDesc(

    val description: String,
    override val id: Int,
    override val imageLink: String,
    override val name: String

) : HeroCard(id, imageLink, name)


data class HeroCardWithBack(

    val backgroundColor: Color,
    override val id: Int,
    override val imageLink: String,
    override val name: String

) : HeroCard(id, imageLink, name)
