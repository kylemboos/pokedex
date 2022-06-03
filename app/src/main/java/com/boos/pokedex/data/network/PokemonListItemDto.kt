package com.boos.pokedex.data.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonListItemDto(
    val name: String = "",
    val url: String = ""
)