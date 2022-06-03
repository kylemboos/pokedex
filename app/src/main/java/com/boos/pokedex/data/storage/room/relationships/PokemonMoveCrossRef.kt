package com.boos.pokedex.data.storage.room.relationships

import androidx.room.Entity

@Entity(primaryKeys = ["pokemonName", "moveId"])
data class PokemonMoveCrossRef(
    val pokemonName: String = "",
    val moveId: Long,
)
