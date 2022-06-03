package com.boos.pokedex.di

import com.boos.pokedex.data.storage.PokemonDataSource
import com.boos.pokedex.data.storage.room.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class PokemonMemoryDataSourceModule {
//
//    @Singleton
//    @Binds
//    abstract fun bindPokemonDao(inMemoryDao: PokemonInMemoryDao): PokemonDataSource
//}

@Module
@InstallIn(SingletonComponent::class)
class PokemonDataSourceModule {


    @Singleton
    @Provides
    fun providePokemonDataSource(pokemonDatabase: PokemonDatabase): PokemonDataSource {
        return pokemonDatabase.pokemonDao
    }
}