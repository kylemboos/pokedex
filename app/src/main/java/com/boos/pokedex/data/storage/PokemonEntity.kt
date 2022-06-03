package com.boos.pokedex.data.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false)
    val pokemonName: String = "",
    val imageUrl: String = "",
    val detailsUrl: String = "",
    val number: String = "",
    val height: String = "",
    val weight: String = ""
)
