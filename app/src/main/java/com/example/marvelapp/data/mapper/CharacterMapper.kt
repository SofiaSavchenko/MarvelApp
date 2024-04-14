package com.example.marvelapp.data.mapper

import com.example.marvelapp.domain.model.CharacterUi
import com.example.marvelapp.data.local.model.CharacterEntity
import com.example.marvelapp.data.remote.model.CharacterDTO

fun CharacterDTO.toCharacterUiFromDTO(): CharacterUi {

    image.path = image.path.replaceFirst("http://", "https://")

    return CharacterUi(
        id = id,
        name = name,
        description = description,
        imageUrl = "${image.path}.${image.extension}"
    )
}

fun List<CharacterDTO>.toCharacterListUiFromDTO(): List<CharacterUi> {

    return this.map { character ->

        character.image.path = character.image.path.replaceFirst("http://", "https://")

        CharacterUi(
            id = character.id,
            name = character.name,
            description = character.description,
            imageUrl = "${character.image.path}.${character.image.extension}"
        )
    }
}

fun CharacterEntity.toCharacterUiFromEntity() = CharacterUi(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl
)

fun List<CharacterEntity>.toCharacterListUiFromEntity(): List<CharacterUi> {

    return this.map { character ->

        CharacterUi(
            id = character.id,
            name = character.name,
            description = character.description,
            imageUrl = character.imageUrl
        )
    }
}

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
