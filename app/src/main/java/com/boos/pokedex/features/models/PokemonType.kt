package com.boos.pokedex.features.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonType(
    val slot: String = "",
    val url: String = "",
    val pokeType: PokeType = PokeType.Normal
): Parcelable