@file:OptIn(ExperimentalMaterialApi::class)

package com.boos.pokedex.features.details

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.boos.pokedex.features.models.PokeType
import com.boos.pokedex.features.models.PokemonModel
import com.boos.pokedex.features.models.PokemonType
import com.boos.pokedex.theme.PokedexTheme
import com.boos.pokedex.util.BottomArcShape
import com.boos.pokedex.util.withPokedexLeadingZeros

@Composable
fun DetailsUi(
    uiState: DetailsUiState,
) {
    PokedexTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                PokemonImageDetail(pokemon = uiState.pokemon)

                var isAboutSelected by remember { mutableStateOf(true) }
                var isStatsSelected by remember { mutableStateOf(false) }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HighlightButton(
                        highlightColor = uiState.pokemon.getTypeColor(),
                        isHighlighted = isAboutSelected,
                        onClick = {
                            isAboutSelected = true
                            isStatsSelected = false
                        },
                        text = "About"
                    )
                    HighlightButton(
                        highlightColor = uiState.pokemon.getTypeColor(),
                        isHighlighted = isStatsSelected,
                        onClick = {
                            isStatsSelected = true
                            isAboutSelected = false
                        },
                        text = "Stats"
                    )
                }

                if (isAboutSelected) {
                    AboutDetails(pokemon = uiState.pokemon)
                } else if (isStatsSelected) {
                    StatsDetails(pokemon = uiState.pokemon)
                }
            }
        }
    }
}

@Composable
private fun HighlightButton(
    isHighlighted: Boolean = false,
    highlightColor: Color,
    onClick: () -> Unit,
    text: String
) {
    val textColor = if (isHighlighted) highlightColor else Color.Gray
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        modifier = Modifier
            .padding(10.dp),
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            color = textColor,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun PokemonImageDetail(pokemon: PokemonModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 325.dp)
            .graphicsLayer {
                shape = BottomArcShape()
                clip = true
            }
            .background(
                color = pokemon.getTypeColor(alpha = .65f),
            )

    ) {
        val (number, name, types, image) = createRefs()

        Text(text = "#${pokemon.number.withPokedexLeadingZeros()}",
            fontSize = 20.sp,
            color = Color.White.copy(alpha = 0.7f),
            modifier = Modifier
                .constrainAs(number) {
                    start.linkTo(parent.start, margin = 16.dp)
                    top.linkTo(parent.top, margin = 16.dp)
                }
        )

        Text(text = pokemon.name.replaceFirstChar { it.uppercase() },
            fontSize = 32.sp,
            color = Color.White.copy(alpha = 0.95f),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(number.bottom)
                start.linkTo(number.start)
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
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(4.dp))
            }
        }

        AsyncImage(
            model = pokemon.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, margin = 50.dp)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

@Composable
fun AboutDetails(pokemon: PokemonModel) {
    val scrollableState = rememberScrollState(0)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(scrollableState)
    ) {
        val (abilities, moves) = createRefs()
        Column(modifier = Modifier.constrainAs(abilities) {
            start.linkTo(parent.start)
        }) {
            PokedexData(pokemon = pokemon)
            Spacer(modifier = Modifier.height(16.dp))
            PokemonAbilities(pokemon = pokemon)
        }
        Column(modifier = Modifier
            .padding(end = 16.dp)
            .constrainAs(moves) {
                end.linkTo(parent.end)
            }) {
            PokemonMoves(pokemon = pokemon)
        }
    }
}

@Composable
fun PokemonAbilities(pokemon: PokemonModel) {
    Text(
        text = "Abilities",
        color = Color.Red.copy(alpha = .8f),
        fontSize = 20.sp
    )
    pokemon.abilities.forEach { ability ->
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.Start) {
            Text(
                text = ability.name.replaceFirstChar { it.uppercase() } + " (${ability.slot})",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            val isHiddenIcon = if (ability.isHidden) {
                Icons.Default.VisibilityOff
            } else {
                Icons.Default.Visibility
            }
            Icon(imageVector = isHiddenIcon, null)
        }
    }
}

@Composable
fun PokemonMoves(pokemon: PokemonModel) {
    Text(
        text = "Moves",
        color = Color.Red.copy(alpha = .8f),
        fontSize = 20.sp
    )
    pokemon.moves.forEach { ability ->
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = ability.name.replaceFirstChar { it.uppercase() },
            fontSize = 18.sp
        )
    }
}


@Composable
private fun PokedexData(pokemon: PokemonModel) {
    Text(
        text = "Pokédex data",
        color = Color.Red.copy(alpha = .8f),
        fontSize = 20.sp
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(horizontalArrangement = Arrangement.Start) {
        Text(
            text = "Height"
        )
        Spacer(modifier = Modifier.width(22.dp))
        Text(
            text = pokemon.height
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(horizontalArrangement = Arrangement.Start) {
        Text(
            text = "Weight"
        )
        Spacer(modifier = Modifier.width(22.dp))
        Text(
            text = pokemon.weight
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun StatsDetails(pokemon: PokemonModel) {
    val scrollableState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(scrollableState)
    ) {
        Text(
            text = "Base Stats",
            color = Color.Red.copy(alpha = .8f),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        pokemon.stats.forEach { stat ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stat.name.replaceFirstChar { it.uppercase() },
                    modifier = Modifier.width(80.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(stat.baseStat, modifier = Modifier.width(32.dp))
                Spacer(modifier = Modifier.width(8.dp))
                val statPercent = stat.baseStat.toFloat() / 255f
                val maxDp = 250.dp
                LinearProgressIndicator(
                    progress = statPercent,
                    color = pokemon.getTypeColor(alpha = 0.85f),
                    backgroundColor = pokemon.getTypeColor(alpha = 0.25f),
                    modifier = Modifier
                        .height(8.dp)
                        .width(150.dp)
                        .clip(RoundedCornerShape(16.dp))
                    //.background(pokemon.getTypeColor(alpha = 0.65f))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text("255")
            }
        }
    }
}

@Preview
@Composable
fun PokemonImageDetailPreview() {
    val pokemon = PokemonModel(
        name = "bulbasaur",
        imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png",
        number = "1",
        types = listOf(
            PokemonType(pokeType = PokeType.Grass),
            PokemonType(pokeType = PokeType.Poison)
        )
    )

    PokedexTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            PokemonImageDetail(pokemon)
        }
    }
}