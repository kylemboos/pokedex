package com.boos.pokedex.features.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.boos.pokedex.features.models.PokemonModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pokemonModel = savedStateHandle.get<PokemonModel>("pokemonModel")
        ?: throw IllegalStateException("Pokemon Model not passed")

    var detailsUiState: DetailsUiState by mutableStateOf(DetailsUiState(pokemonModel))

    init {
        val updatedStats = pokemonModel.stats.map { stat ->
            val updatedName = stat.name
                .replace("special-attack", "sp. Atk")
                .replace("special-defense", "sp. Def")
            stat.copy(name = updatedName)
        }
        detailsUiState = detailsUiState.copy(
            pokemon = pokemonModel.copy(
                stats = updatedStats
            )
        )
    }
}