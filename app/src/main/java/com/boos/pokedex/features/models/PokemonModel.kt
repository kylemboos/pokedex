package com.boos.pokedex.features.models

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonModel(
    val name: String = "",
    val imageUrl: String = "",
    val detailsUrl: String = "",
    val number: String = "",
    val height: String = "",
    val weight: String = "",
    val moves: List<PokemonMove> = emptyList(),
    val abilities: List<PokemonAbility> = emptyList(),
    val stats: List<PokemonStat> = emptyList(),
    val types: List<PokemonType> = emptyList()
):Parcelable {
    fun getTypeColor(fallbackColor: Color = Color.Gray, alpha: Float = 1.0f): Color {
        return types.firstOrNull()?.pokeType?.color?.copy(alpha) ?: fallbackColor
    }
}