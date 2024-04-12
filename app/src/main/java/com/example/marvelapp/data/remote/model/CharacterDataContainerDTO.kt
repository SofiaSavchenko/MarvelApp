package com.example.marvelapp.data.remote.model

import com.squareup.moshi.Json

data class CharacterDataContainerDTO (
    @field:Json(name = "results")
    val results: List<CharacterDTO>
)
