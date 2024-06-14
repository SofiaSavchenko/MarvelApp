package com.example.network.data.models

import com.squareup.moshi.Json

data class CharacterDataWrapperDTO(
    @field:Json(name = "data")
    val data: CharacterDataContainerDTO
)
