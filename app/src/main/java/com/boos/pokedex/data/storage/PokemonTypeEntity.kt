package com.boos.pokedex.data.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonTypeEntity(
    @PrimaryKey(autoGenerate = true)
    val typeId: Long? = null,
    val typeName: String = "",
    val slot: String = "",
    val url: String = "",
    val pokemonName: String = ""
)
