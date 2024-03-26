package com.example.marvelapp.network.model

import com.squareup.moshi.Json

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    @field: Json(name = "thumbnail")
    val image: Image
)

data class Image(
    var path: String,
    val extension: String
)