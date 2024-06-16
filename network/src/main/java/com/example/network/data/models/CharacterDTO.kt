package com.example.network.data.models

import com.squareup.moshi.Json

data class CharacterDTO(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "description")
    val description: String,
    @field: Json(name = "thumbnail")
    val image: ImageDTO
)

data class ImageDTO(
    @field:Json(name = "path")
    var path: String,
    @field:Json(name = "extension")
    val extension: String
)
