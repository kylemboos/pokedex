package com.boos.pokedex.di

import android.content.Context
import androidx.room.Room
import com.boos.pokedex.data.storage.room.PokemonDao
import com.boos.pokedex.data.storage.room.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PokemonDatabaseModule {

    @Singleton
    @Provides
    fun providePokemonDatabase(@ApplicationContext applicationContext: Context): PokemonDatabase {
        return Room.inMemoryDatabaseBuilder(
            applicationContext,
            PokemonDatabase::class.java,
        ).build()
    }

    @Singleton
    @Provides
    fun providePokemonDao(pokemonDatabase: PokemonDatabase): PokemonDao {
        return pokemonDatabase.pokemonDao
    }
}