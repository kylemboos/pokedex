package com.boos.pokedex.features.models.mappers

import com.boos.pokedex.data.network.*
import com.boos.pokedex.data.storage.*
import com.boos.pokedex.data.storage.room.PokemonCompleteData
import com.boos.pokedex.data.storage.room.relationships.PokemonAbilityCrossRef
import com.boos.pokedex.data.storage.room.relationships.PokemonMoveCrossRef
import com.boos.pokedex.util.withPokedexLeadingZeros
import com.boos.pokedex.features.models.*



fun PokemonCompleteData.toPokemonModel(): PokemonModel =
    PokemonModel(
        name = pokemon.pokemonName,
        imageUrl = pokemon.imageUrl,
        detailsUrl = pokemon.detailsUrl,
        number = pokemon.number,
        height = pokemon.height,
        weight = pokemon.weight,
        moves = moves.mapToPokemonMoves(),
        abilities = abilities.mapToPokemonAbilities(),
        stats = stats.mapToPokemonStats(),
        types = types.mapToPokemonTypes()
    )

private fun List<PokemonMoveEntity>.mapToPokemonMoves(): List<PokemonMove> {
    return map {
        PokemonMove(
            name = it.moveName,
            url = it.url
        )
    }
}

private fun List<PokemonAbilityEntity>.mapToPokemonAbilities(): List<PokemonAbility> {
    return map {
        PokemonAbility(
            name = it.abilityName,
            url = it.url,
            isHidden = it.isHidden,
            slot = it.slot
        )
    }
}

private fun List<PokemonStatEntity>.mapToPokemonStats(): List<PokemonStat> {
    return map{
        PokemonStat(
            name = it.statName,
            url = it.url,
            effort = it.effort,
            baseStat = it.baseStat
        )
    }
}

private fun List<PokemonTypeEntity>.mapToPokemonTypes(): List<PokemonType> {
    return map{
        PokemonType(
            url = it.url,
            slot = it.slot,
            pokeType = getPokemonType(it.typeName)
        )
    }
}

fun PokemonEntity.updatePokemonDetails(detail: PokemonDetailDto): PokemonEntity {
    return copy(
        height = detail.height.toString(),
        weight = detail.weight.toString(),
    )
}

fun List<Long>.toAbilityCrossRefs(pokemonName: String): List<PokemonAbilityCrossRef> {
    return map {
        PokemonAbilityCrossRef(
            pokemonName = pokemonName,
            abilityId = it
        )
    }
}

fun List<Long>.toMoveCrossRefs(pokemonName: String): List<PokemonMoveCrossRef> {
    return map {
        PokemonMoveCrossRef(
            pokemonName = pokemonName,
            moveId = it
        )
    }
}