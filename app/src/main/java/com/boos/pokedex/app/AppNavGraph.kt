package com.boos.pokedex.app

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.boos.pokedex.app.destinations.DetailsScreenDestination
import com.boos.pokedex.features.browse.BrowseUi
import com.boos.pokedex.features.browse.BrowseUiEvent
import com.boos.pokedex.features.browse.BrowseViewModel
import com.boos.pokedex.features.details.DetailsUi
import com.boos.pokedex.features.details.DetailsViewModel
import com.boos.pokedex.features.models.PokemonModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun BrowseScreen(
    viewModel: BrowseViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    BrowseUi(
        uiState = viewModel.uiState,
        onPokemonClick = { pokemonModel ->
            navigator.navigate(DetailsScreenDestination(pokemonModel))
        },
        onRefreshSwipe = {
            viewModel.handleUiEvent(BrowseUiEvent.RefreshSwipe)
        },
        onSearchUpdated = { query ->
            viewModel.handleUiEvent(BrowseUiEvent.SearchUpdated(query))
        }
    )
}

@Composable
@Destination
fun DetailsScreen(
    pokemonModel: PokemonModel, //Compose-destination will allow viewModel access via savedStateHandle param
    viewModel: DetailsViewModel = hiltViewModel()
) {
    DetailsUi(
        uiState = viewModel.detailsUiState
    )
}