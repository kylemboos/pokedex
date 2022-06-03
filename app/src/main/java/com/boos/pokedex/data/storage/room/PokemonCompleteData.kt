package com.boos.pokedex.data.storage.room

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.boos.pokedex.data.storage.*
import com.boos.pokedex.data.storage.room.relationships.PokemonAbilityCrossRef
import com.boos.pokedex.data.storage.room.relationships.PokemonMoveCrossRef

data class PokemonCompleteData(
    @Embedded val pokemon: PokemonEntity,

    @Relation(
        parentColumn = "pokemonName",
        entityColumn = "moveId",
        associateBy = Junction(PokemonMoveCrossRef::class)
    )
    val moves: List<PokemonMoveEntity> = emptyList(),

    @Relation(
        parentColumn = "pokemonName",
        entityColumn = "abilityId",
        associateBy = Junction(PokemonAbilityCrossRef::class)
    )
    val abilities: List<PokemonAbilityEntity> = emptyList(),

    @Relation(
        parentColumn = "pokemonName",
        entityColumn = "pokemonName"
    )
    val stats: List<PokemonStatEntity> = emptyList(),

    @Relation(
        parentColumn = "pokemonName",
        entityColumn = "pokemonName"
    )
    val types: List<PokemonTypeEntity> = emptyList(),
)
