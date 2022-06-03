package com.boos.pokedex.features.browse

import com.boos.pokedex.util.Result
import com.boos.pokedex.features.models.PokemonModel
import kotlinx.coroutines.flow.Flow

interface BrowseRepository {

    suspend fun getPokemonModels(
        query: String = "",
        useRemote: Boolean = false
    ): Flow<Result<List<PokemonModel>>>

}
