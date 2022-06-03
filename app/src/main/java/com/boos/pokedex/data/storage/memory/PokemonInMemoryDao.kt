package com.boos.pokedex.data.storage.memory

import com.boos.pokedex.data.storage.PokemonDataSource
import com.boos.pokedex.data.storage.PokemonEntity
import javax.inject.Inject

//rendered out of date by cross reference entity requirements of Room

//class PokemonInMemoryDao @Inject constructor(): PokemonDataSource {
//
//    private val pokemon: MutableMap<String, PokemonEntity> = mutableMapOf()
//
//    override suspend fun insertPokemon(pokemonModel: List<PokemonEntity>) {
//        pokemonModel.forEach {
//            pokemon[it.pokemonName] = it
//        }
//    }
//
//    override suspend fun findPokemon(name: String): PokemonEntity {
//        return pokemon[name] ?: throw IllegalAccessException("Pokemon not found in memory")
//    }
//
//    override suspend fun searchPokemonList(query: String): List<PokemonEntity> {
//        if(query.isBlank()) return pokemon.values.toList()
//        return pokemon.filter { it.key.contains(query) }.values.toList()
//    }
//
//    override suspend fun clearPokemon() {
//        pokemon.clear()
//    }
//
//}