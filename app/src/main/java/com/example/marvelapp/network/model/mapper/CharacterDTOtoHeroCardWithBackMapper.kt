package com.example.marvelapp.network.model.mapper

import androidx.compose.ui.graphics.Color
import com.example.marvelapp.data.HeroCardWithBack
import com.example.marvelapp.network.model.CharacterDTO

fun CharacterDTO.toHeroCardWithBack(color: Color) = HeroCardWithBack(
    id = id,
    backgroundColor = color,
    imageLink = "${image.path}.${image.extension}",
    name = name
)