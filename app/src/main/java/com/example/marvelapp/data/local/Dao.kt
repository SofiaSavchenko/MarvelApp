package com.example.marvelapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelapp.data.local.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM characters_table ORDER BY name ASC")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM characters_table WHERE id = :id")
    fun getCharacterById(id: Int): Flow<CharacterEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllCharacters(characters: List<CharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCharacter(character: CharacterEntity)

    @Query("DELETE FROM characters_table")
    suspend fun deleteAllCharacters()

}