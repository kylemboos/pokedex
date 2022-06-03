package com.boos.pokedex.data.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonAbilityEntity(
    @PrimaryKey(autoGenerate = true)
    val abilityId: Long? = null,
    val abilityName: String = "",
    val url: String = "",
    val isHidden: Boolean = false,
    val slot: String = ""
)
