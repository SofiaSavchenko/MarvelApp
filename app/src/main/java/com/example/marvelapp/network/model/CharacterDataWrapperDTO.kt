package com.example.marvelapp.network.model

import com.squareup.moshi.Json

data class CharacterDataWrapperDTO(
    @field:Json(name = "data")
    val data: CharacterDataContainerDTO
)