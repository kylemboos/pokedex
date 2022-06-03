package com.boos.pokedex.data.mappers

import com.boos.pokedex.data.network.PokemonDetailDto
import com.boos.pokedex.data.network.PokemonListItemDto
import com.boos.pokedex.data.storage.*
import com.boos.pokedex.util.withPokedexLeadingZeros

fun List<PokemonListItemDto>.toPokemonEntities(): List<PokemonEntity> {
    return map { pokemonListItemModel ->
        val number = parsePokemonNumber(pokemonListItemModel.url)
        PokemonEntity(
            pokemonName = pokemonListItemModel.name,
            imageUrl = buildPokemonImageUrl(number),
            detailsUrl = pokemonListItemModel.url,
            number = number
        )
    }
}

private fun parsePokemonNumber(url: String): String {
    return url
        .replace("https://pokeapi.co/api/v2/pokemon/", "")
        .replace("/", "")
}

private fun buildPokemonImageUrl(number: String): String {
    val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/"
    val pokemonNumber = number.withPokedexLeadingZeros()
    return "$imageUrl$pokemonNumber.png"
}

fun PokemonDetailDto.toPokemonAbilityEntities(): List<PokemonAbilityEntity> {
    return abilities.map {
        PokemonAbilityEntity(
            abilityName = it.ability.name,
            url = it.ability.url,
            isHidden = it.isHidden,
            slot = it.slot.toString()
        )
    }
}

fun PokemonDetailDto.toPokemonMoveEntities(): List<PokemonMoveEntity> {
    return moves.map {
        PokemonMoveEntity(
            moveName = it.move.name,
            url = it.move.url
        )
    }
}

fun PokemonDetailDto.toPokemonTypeEntities(pokemonName: String): List<PokemonTypeEntity> {
    return types.map {
        PokemonTypeEntity(
            typeName = it.type.name,
            url = it.type.url,
            slot = it.slot.toString(),
            pokemonName = pokemonName
        )
    }
}

fun PokemonDetailDto.toPokemonStatEntities(pokemonName: String): List<PokemonStatEntity> {
    return stats.map {
        PokemonStatEntity(
            statName = it.stat.name,
            url = it.stat.url,
            effort = it.effort.toString(),
            baseStat = it.baseStat.toString(),
            pokemonName = pokemonName
        )
    }
}