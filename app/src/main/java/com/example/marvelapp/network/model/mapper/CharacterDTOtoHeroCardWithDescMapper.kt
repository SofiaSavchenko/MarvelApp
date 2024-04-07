package com.example.marvelapp.network.model.mapper

import com.example.marvelapp.data.HeroCardWithDesc
import com.example.marvelapp.network.model.CharacterDTO

fun CharacterDTO.toHeroCardWithDesc() = HeroCardWithDesc(
    id = id,
    description = description,
    imageLink = "${image.path}.${image.extension}",
    name = name
)