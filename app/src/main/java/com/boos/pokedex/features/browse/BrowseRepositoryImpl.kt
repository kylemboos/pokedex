package com.boos.pokedex.features.browse

import com.boos.pokedex.data.mappers.*
import com.boos.pokedex.data.network.PokemonApi
import com.boos.pokedex.data.storage.PokemonDataSource
import com.boos.pokedex.features.models.PokemonModel
import com.boos.pokedex.features.models.mappers.*
import com.boos.pokedex.util.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BrowseRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi,
    private val pokemonDataSource: PokemonDataSource
) : BrowseRepository {


    override suspend fun getPokemonModels(
        query: String,
        useRemote: Boolean
    ): Flow<Result<List<PokemonModel>>> {
        return flow {
            emit(Result.Loading(isLoading = true))

            val shouldFetchRemote = pokemonDataSource.searchPokemonList("").isEmpty() || useRemote
            if (shouldFetchRemote) {
                try {
                    pokemonDataSource.clearPokemon()
                    pokemonDataSource.clearMoves()
                    pokemonDataSource.clearAbilities()
                    pokemonDataSource.clearTypes()
                    pokemonDataSource.clearStats()
                    pokemonDataSource.clearAbilityCrossRefs()
                    pokemonDataSource.clearMoveCrossRefs()

                    val pokemonListItems = pokemonApi.getPokemonList().results
                    pokemonDataSource.insertPokemon(pokemonListItems.toPokemonEntities())
                    val pokemon = if(query.isBlank()) {
                        pokemonDataSource.getPokemon()
                    } else {
                        pokemonDataSource.searchPokemonList(query)
                    }
                    emit(Result.Success(pokemon.map { it.toPokemonModel() }))
                    coroutineScope {
                        pokemonListItems.map {
                            async {
                                val detailDto = pokemonApi.getPokemonByName(it.name)
                                val model = pokemonDataSource.findPokemon(it.name)
                                val updatedPokemon = model.updatePokemonDetails(detailDto)
                                val moves = detailDto.toPokemonMoveEntities()
                                val abilities = detailDto.toPokemonAbilityEntities()
                                val types = detailDto.toPokemonTypeEntities(it.name)
                                val stats = detailDto.toPokemonStatEntities(it.name)

                                pokemonDataSource.insertPokemon(listOf(updatedPokemon))
                                pokemonDataSource.insertTypes(types)
                                pokemonDataSource.insertStats(stats)

                                val moveIds = pokemonDataSource.insertMoves(moves)
                                val moveCrossRefs = moveIds.toMoveCrossRefs(it.name)
                                pokemonDataSource.insertMoveCrossRef(moveCrossRefs)

                                val abilityIds = pokemonDataSource.insertAbilities(abilities)
                                val abilityCrossRefs = abilityIds.toAbilityCrossRefs(it.name)
                                pokemonDataSource.insertAbilityCrossRef(abilityCrossRefs)

                            }
                        }
                            .awaitAll()
                    }
                } catch (e: Exception) {
                    println("ERROR FETCHING POKEMON")
                    print(e.stackTrace)
                    emit(Result.Error(message = e.message.toString()))
                }
            }
            val pokemonModels = if(query.isBlank()) {
                pokemonDataSource.getPokemon()
            } else {
                pokemonDataSource.searchPokemonList(query)
            }
            emit(Result.Success(pokemonModels.map { it.toPokemonModel() }))

            emit(Result.Loading(isLoading = false))
        }
    }
}