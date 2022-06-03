package com.boos.pokedex.features.browse

import com.boos.pokedex.MainDispatcherRule
import com.boos.pokedex.data.storage.PokemonModel
import com.boos.pokedex.features.models.PokeType
import com.boos.pokedex.features.models.PokemonType
import com.boos.pokedex.util.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class BrowseViewModelTest {

    // add coroutine main dispatcher test rule
    // add mock web server support
    @get:Rule val mainDispatcherRule = MainDispatcherRule()

    private val repository = mockk<BrowseRepository>()
    private lateinit var viewModel: BrowseViewModel

    private val fakePokemonList = listOf(
        PokemonModel(
            name = "Squirtle",
            types = listOf(
                PokemonType(pokeType = PokeType.Water))
        )
    )

    private val successResult: Flow<Result<List<PokemonModel>>> =
        flow {
            emit(Result.Success(fakePokemonList))
        }

    @Before
    fun setup() {
        coEvery { repository.getPokemonModels(any(), any()) } returns flow{}
        viewModel = BrowseViewModel(repository)
    }


    @Test
    fun `GIVEN WHEN RefreshSwipe uiEvent is handled THEN search items are fetched from repository with remote flag`() =
        runTest {
            coEvery { repository.getPokemonModels(any(), true) } returns successResult

            viewModel.handleUiEvent(BrowseUiEvent.RefreshSwipe)

            advanceUntilIdle()
            assertEquals(fakePokemonList, viewModel.uiState.pokemonList)
        }
}