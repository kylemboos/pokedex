package com.boos.pokedex.features.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonAbility(
    val name: String = "",
    val url: String = "",
    val isHidden: Boolean = false,
    val slot: String = ""
): Parcelable
