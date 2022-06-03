package com.boos.pokedex.data.storage.room

import androidx.room.*
import com.boos.pokedex.data.storage.*
import com.boos.pokedex.data.storage.room.relationships.PokemonAbilityCrossRef
import com.boos.pokedex.data.storage.room.relationships.PokemonMoveCrossRef

@Dao
interface PokemonDao : PokemonDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertPokemon(pokemonEntity: List<PokemonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertAbilities(abilityEntity: List<PokemonAbilityEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertMoves(moveEntity: List<PokemonMoveEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertStats(statEntity: List<PokemonStatEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertTypes(typeEntity: List<PokemonTypeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertAbilityCrossRef(abilityCrossRefs: List<PokemonAbilityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertMoveCrossRef(moveCrossRefs: List<PokemonMoveCrossRef>)

    @Query(
        """
        SELECT * 
        FROM pokemonentity 
        WHERE :name = pokemonName 
        LIMIT 1
    """
    )
    override suspend fun findPokemon(name: String): PokemonEntity


    @Transaction
    @Query(
        """
            SELECT *
            FROM pokemonentity
            WHERE LOWER(pokemonName) LIKE '%' || :query || '%'
        """
    )
    override suspend fun searchPokemonList(query: String): List<PokemonCompleteData>

    @Transaction
    @Query(
        """
        SELECT *
        FROM PokemonEntity
    """
    )
    override suspend fun getPokemon(): List<PokemonCompleteData>

    @Query("DELETE FROM pokemonentity")
    override suspend fun clearPokemon()

    @Query("DELETE FROM pokemonmoveentity")
    override suspend fun clearMoves()

    @Query("DELETE FROM pokemonabilityentity")
    override suspend fun clearAbilities()

    @Query("DELETE FROM pokemonstatentity")
    override suspend fun clearStats()

    @Query("DELETE FROM pokemontypeentity")
    override suspend fun clearTypes()

    @Query("DELETE FROM pokemonmovecrossref")
    override suspend fun clearMoveCrossRefs()

    @Query("DELETE FROM pokemonabilitycrossref")
    override suspend fun clearAbilityCrossRefs()
}