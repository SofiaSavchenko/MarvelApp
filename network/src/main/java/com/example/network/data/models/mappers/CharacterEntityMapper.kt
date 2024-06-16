package com.example.network.data.models.mappers

import com.example.database.models.CharacterEntity
import com.example.network.data.models.CharacterDTO

fun CharacterDTO.toCharacterEntityFromDTO(): CharacterEntity {

    image.path = image.path.replaceFirst("http://", "https://")

    return CharacterEntity(
        id = id,
        name = name,
        description = description,
        imageUrl = "${image.path}.${image.extension}"
    )
}

fun List<CharacterDTO>.toCharacterListEntityFromDTO(): List<CharacterEntity> {

    return this.map { character ->

        character.image.path = character.image.path.replaceFirst("http://", "https://")

        CharacterEntity(
            id = character.id,
            name = character.name,
            description = character.description,
            imageUrl = "${character.image.path}.${character.image.extension}"
        )
    }
}
