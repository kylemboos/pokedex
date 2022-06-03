package com.boos.pokedex.data.storage.room.relationships

import androidx.room.Entity

@Entity(primaryKeys = ["pokemonName", "abilityId"])
data class PokemonAbilityCrossRef(
    val pokemonName: String = "",
    val abilityId: Long,
)