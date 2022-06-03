package com.boos.pokedex.data.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.boos.pokedex.data.storage.*
import com.boos.pokedex.data.storage.room.relationships.PokemonAbilityCrossRef
import com.boos.pokedex.data.storage.room.relationships.PokemonMoveCrossRef

@Database(
    entities = [PokemonEntity::class,
        PokemonMoveEntity::class,
        PokemonStatEntity::class,
        PokemonTypeEntity::class,
        PokemonAbilityEntity::class,
        PokemonMoveCrossRef::class,
        PokemonAbilityCrossRef::class],
    version = 1
)
abstract class PokemonDatabase : RoomDatabase() {

    abstract val pokemonDao: PokemonDao
}