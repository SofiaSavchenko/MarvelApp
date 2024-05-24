package com.example.network.data.models

import com.squareup.moshi.Json

data class CharacterDataContainerDTO (
    @field:Json(name = "results")
    val results: List<CharacterDTO>
)
