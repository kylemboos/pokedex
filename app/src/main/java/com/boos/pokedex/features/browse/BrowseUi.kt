package com.boos.pokedex.features.browse

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.boos.pokedex.R
import com.boos.pokedex.theme.PokedexTheme
import com.boos.pokedex.util.withPokedexLeadingZeros
import com.boos.pokedex.features.models.PokeType
import com.boos.pokedex.features.models.PokemonModel
import com.boos.pokedex.features.models.PokemonType
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun BrowseUi(
    uiState: BrowseUiState,
    onSearchUpdated: (String) -> Unit,
    onPokemonClick: (pokemonModel: PokemonModel) -> Unit,
    onRefreshSwipe: () -> Unit
) {
    val refreshState = rememberSwipeRefreshState(isRefreshing = uiState.isRefreshing)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Pokédex",
            color = Color.Red.copy(alpha = .8f),
            fontSize = 36.sp
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = uiState.query,
            onValueChange = { onSearchUpdated(it) },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(Icons.Default.Search, stringResource(R.string.searchPokemon)) },
            placeholder = { Text("Search for Pokémon") },
            maxLines = 1,
            singleLine = true,
            shape = RoundedCornerShape(40.dp)
        )
        Spacer(Modifier.height(16.dp))

        SwipeRefresh(
            state = refreshState,
            onRefresh = { onRefreshSwipe() }) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.pokemonList.size) { index ->
                    if (index == 0) {
                        Text(
                            "A lightweight Pokédex to sort types and browse stats from the Pokemon series",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                    val pokemon = uiState.pokemonList[index]
                    PokemonListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onPokemonClick(pokemon) }
                            .padding(16.dp),
                        pokemon = pokemon
                    )
                    if (index == uiState.pokemonList.size-1) {
                        Text(
                            "End of Supported Pokédex data. This App is supported by the free Pokémon API at https://pokeapi.co/.",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonListItem(
    modifier: Modifier,
    pokemon: PokemonModel
) {
    ConstraintLayout(
        modifier = modifier
            .clip(RoundedCornerShape(40.dp))
            .background(
                color = pokemon.types.firstOrNull()?.pokeType?.color?.copy(alpha = .65f)
                    ?: Color.Gray
            )
    ) {
        val (image, name, types, number) = createRefs()
        AsyncImage(
            model = pokemon.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .constrainAs(image) {
                    start.linkTo(parent.start, margin = 8.dp)
                    top.linkTo(parent.top, margin = 8.dp)
                    bottom.linkTo(parent.bottom, margin = 8.dp)
                }
        )
        Text(text = pokemon.name.replaceFirstChar { it.uppercase() },
            fontSize = 24.sp,
            color = Color.White.copy(alpha = 0.95f),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(name) {
                start.linkTo(image.end, margin = 16.dp)
                top.linkTo(parent.top, margin = 8.dp)
            }
        )
        Row(modifier = Modifier.constrainAs(types) {
            top.linkTo(name.bottom, margin = 8.dp)
            start.linkTo(name.start)
        }) {
            pokemon.types.forEach { type ->
                AsyncImage(
                    model = type.pokeType.imageAddress,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(4.dp))
            }
        }
        Text(text = "#${pokemon.number.withPokedexLeadingZeros()}",
            fontSize = 40.sp,
            color = Color.White.copy(alpha = 0.7f),
            modifier = Modifier
                .constrainAs(number) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end, margin = 24.dp)
                }
        )
    }
}

@Preview
@Composable
fun PokemonListItemPreview(
    pokemon: PokemonModel = PokemonModel(
        name = "bulbasaur",
        imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png",
        number = "1",
        types = listOf(
            PokemonType(pokeType = PokeType.Grass),
            PokemonType(pokeType = PokeType.Poison)
        )
    )
) {
    PokedexTheme {
        PokemonListItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), pokemon = pokemon
        )
    }
}