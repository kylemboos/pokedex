package com.boos.pokedex.features.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonStat(
    val name: String = "",
    val url: String = "",
    val effort: String = "",
    val baseStat: String = "",
): Parcelable
