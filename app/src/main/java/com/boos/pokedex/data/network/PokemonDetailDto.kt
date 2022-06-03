package com.boos.pokedex.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDetailDto(
    val abilities: List<PokemonAbilityDto> = emptyList(),
    @Json(name = "base_experience") val baseExperience: Int = 0,
    val height: Int = 0,
    val moves: List<PokemonMoveDto> = emptyList(),
    val stats: List<PokemonStatDto> = emptyList(),
    val types: List<PokemonTypeDto> = emptyList(),
    val weight: Int = 0
)

data class PokemonAbilityDto(
    val ability: PokemonListItemDto = PokemonListItemDto(),
    @Json(name = "is_hidden") val isHidden: Boolean = false,
    val slot: Int = 1
)

data class PokemonMoveDto(
    val move: PokemonListItemDto = PokemonListItemDto(),
)

data class PokemonStatDto(
    @Json(name = "base_stat") val baseStat: Int = 0,
    val effort: Int = 0,
    val stat: PokemonListItemDto = PokemonListItemDto()
)

data class PokemonTypeDto(
    val slot: Int = 1,
    val type: PokemonListItemDto = PokemonListItemDto(),
)
