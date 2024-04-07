package com.example.marvelapp.network.data.repository

import com.example.marvelapp.data.HeroCardWithBack
import com.example.marvelapp.data.HeroCardWithDesc
import com.example.marvelapp.network.model.ApiError
import com.example.marvelapp.network.model.either.Either

interface Repository {

    suspend fun getMarvelCharacters(): Either<ApiError, List<HeroCardWithBack>>
    suspend fun getMarvelCharacterById(id: Int): Either<ApiError, HeroCardWithDesc>
}