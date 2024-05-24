package com.example.database.models.mappers

import com.example.database.models.CharacterEntity
import com.example.core_ui.models.CharacterUi

fun CharacterEntity.toCharacterUiFromEntity() =
    CharacterUi(
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


