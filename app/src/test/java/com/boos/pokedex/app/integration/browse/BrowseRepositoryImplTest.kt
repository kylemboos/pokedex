package com.boos.pokedex.app.integration.browse

import com.boos.pokedex.addMockResponse
import com.boos.pokedex.features.models.mappers.toPokemonEntities
import com.boos.pokedex.features.models.mappers.toPokemonModel
import com.boos.pokedex.data.network.PokemonApi
import com.boos.pokedex.data.network.PokemonListDto
import com.boos.pokedex.data.storage.PokemonDataSource
import com.boos.pokedex.data.storage.memory.PokemonInMemoryDao
import com.boos.pokedex.di.AppModule
import com.boos.pokedex.util.Result
import com.boos.pokedex.features.browse.BrowseRepositoryImpl
import com.boos.pokedex.features.models.PokemonModel
import com.boos.pokedex.getModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@OptIn(ExperimentalCoroutinesApi::class)
class BrowseRepositoryImplTest {

    private val mockWebServer = MockWebServer()

    private val moshi: Moshi = AppModule.provideMoshi()

    private val pokemonApi: PokemonApi = Retrofit.Builder()
        .baseUrl(mockWebServer.url(""))
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create()

    private val pokemonDataSource: PokemonDataSource = PokemonInMemoryDao()

    private val repository = BrowseRepositoryImpl(
        pokemonApi = pokemonApi,
        pokemonDataSource = pokemonDataSource
    )

    @Before
    fun setup() {
        runBlocking { pokemonDataSource.clearPokemon() }
    }

    @Test
    fun `GIVEN pokemonDataSource is empty and successful list response WHEN getPokemonSearchItems THEN results from api returned`() =
        runTest {
            addMockResponse(mockWebServer, "pokemonListResponse.json")

            val parsedData = parsePokemonListFromFile()
            val expectedResult: Result<List<PokemonModel>> =
                Result.Success(
                    data = parsedData
                )

            val results = repository.getPokemonModels("", true).toList()
            assertTrue(results.contains(expectedResult))
            //val results = repository.getPokemonSearchItems("", true).filter { result -> result is Result.Success}.first()
            //assertEquals(successData, results.resultData)
        }

    @Test
    fun `GIVEN WHEN THEN`() = runTest {

    }

    private fun parsePokemonListFromFile(): List<PokemonModel> {
        val adapter = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            .adapter(PokemonListDto::class.java)

        val listModel = adapter.getModel(this, "pokemonListResponse.json")
        return listModel.results.toPokemonEntities().map{it.toPokemonModel()}
    }

}