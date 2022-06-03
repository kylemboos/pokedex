package com.boos.pokedex.data.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonListDto(
    val count: Int = 0,
    val next: String? = null,
    val previous: String? = null,
    val results: List<PokemonListItemDto> = emptyList()
)

