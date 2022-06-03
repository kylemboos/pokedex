package com.boos.pokedex.features.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonMove(
    val name: String = "",
    val url: String = ""
): Parcelable
