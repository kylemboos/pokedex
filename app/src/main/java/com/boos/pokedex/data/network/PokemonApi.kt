package com.boos.pokedex.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    //898 is max of supported Pokemon data from this API
    //@GET("pokemon?limit=898")
    @GET("pokemon?limit=151")
    suspend fun getPokemonList(): PokemonListDto

    @GET("pokemon/{number}")
    suspend fun getPokemonByNumber(@Path("number") number: String): PokemonDetailDto

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): PokemonDetailDto

    @GET("type")
    suspend fun getPokemonTypes(): PokemonListDto

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}
