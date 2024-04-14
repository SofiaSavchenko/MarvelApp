package com.example.marvelapp.data.remote.model

import com.example.marvelapp.data.remote.model.CharacterDataContainerDTO
import com.squareup.moshi.Json

data class CharacterDataWrapperDTO(
    @field:Json(name = "data")
    val data: CharacterDataContainerDTO
)