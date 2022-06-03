package com.boos.pokedex.data.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonStatEntity(
    @PrimaryKey(autoGenerate = true)
    val statId: Long? = null,
    val statName: String = "",
    val url: String = "",
    val effort: String = "",
    val baseStat: String = "",
    val pokemonName: String = ""
)