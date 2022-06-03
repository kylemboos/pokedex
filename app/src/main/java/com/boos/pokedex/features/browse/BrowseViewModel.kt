package com.boos.pokedex.features.browse

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boos.pokedex.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrowseViewModel @Inject constructor(
    private val repository: BrowseRepository
) : ViewModel() {

    var uiState by mutableStateOf(BrowseUiState())

    init {
        fetchPokemon()
    }

    private fun fetchPokemon(isHardRefresh: Boolean = false) {
        viewModelScope.launch {
            val pokemonResults = repository.getPokemonModels(
                query = uiState.query,
                useRemote = isHardRefresh
            )
            pokemonResults.collect { result ->
                when(result) {
                    is Result.Loading -> uiState = uiState.copy(isLoading = result.isLoading)
                    is Result.Success -> {
                        val pokemonList = result.data.sortedBy { it.number.toInt() }
                        uiState = uiState.copy(pokemonList = pokemonList)
                    }
                    is Result.Error -> {
                        //Error Handling
                    }
                }
            }
        }
    }

    private fun refreshPokemon() {
        fetchPokemon(true)
    }

    fun handleUiEvent(uiEvent: BrowseUiEvent) {
        when(uiEvent) {
            BrowseUiEvent.RefreshSwipe -> {
                refreshPokemon()
            }
            is BrowseUiEvent.SearchUpdated -> {
                uiState = uiState.copy(query = uiEvent.query)
                fetchPokemon()
            }
        }
    }
}
