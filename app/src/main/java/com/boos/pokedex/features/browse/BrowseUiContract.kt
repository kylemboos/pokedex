package com.boos.pokedex.features.browse

import com.boos.pokedex.features.models.PokemonModel


data class BrowseUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val query: String = "",
    val pokemonList: List<PokemonModel> = emptyList()
)

sealed class BrowseUiEvent {
    object RefreshSwipe : BrowseUiEvent()
    data class SearchUpdated(val query: String) : BrowseUiEvent()
}