package com.example.network.data.models.mappers

import com.example.core_ui.models.CharacterUi
import com.example.network.data.models.CharacterDTO

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