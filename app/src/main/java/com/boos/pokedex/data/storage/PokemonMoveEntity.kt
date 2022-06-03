package com.boos.pokedex.data.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonMoveEntity(
    @PrimaryKey(autoGenerate = true)
    val moveId: Long? = null,
    val moveName: String = "",
    val url: String = ""
)
