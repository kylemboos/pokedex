package com.boos.pokedex.data.storage

import com.boos.pokedex.data.storage.room.PokemonCompleteData
import com.boos.pokedex.data.storage.room.relationships.PokemonAbilityCrossRef
import com.boos.pokedex.data.storage.room.relationships.PokemonMoveCrossRef

interface PokemonDataSource {
    suspend fun insertPokemon(pokemonEntity: List<PokemonEntity>)
    suspend fun insertMoves(moveEntity: List<PokemonMoveEntity>): List<Long>
    suspend fun insertAbilities(abilityEntity: List<PokemonAbilityEntity>): List<Long>
    suspend fun insertTypes(typeEntity: List<PokemonTypeEntity>)
    suspend fun insertStats(statEntity: List<PokemonStatEntity>)
    suspend fun insertMoveCrossRef(moveCrossRefs: List<PokemonMoveCrossRef>)
    suspend fun insertAbilityCrossRef(abilityCrossRefs: List<PokemonAbilityCrossRef>)

    suspend fun findPokemon(name: String): PokemonEntity
    suspend fun getPokemon(): List<PokemonCompleteData>
    suspend fun searchPokemonList(query: String): List<PokemonCompleteData>

    suspend fun clearPokemon()
    suspend fun clearMoves()
    suspend fun clearAbilities()
    suspend fun clearTypes()
    suspend fun clearStats()
    suspend fun clearAbilityCrossRefs()
    suspend fun clearMoveCrossRefs()
}