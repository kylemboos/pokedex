package com.boos.pokedex.features.models

import androidx.compose.ui.graphics.Color

enum class PokeType(
    val typeName: String,
    val typeNumber: Int,
    val color: Color = Color.Gray,
    val imageAddress: String = ""
) {
    Normal(
        "normal",
        1,
        color = Color(0xFFA8A77A),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/Pok%C3%A9mon_Normal_Type_Icon.svg/120px-Pok%C3%A9mon_Normal_Type_Icon.svg.png"
    ),
    Fighting(
        "fighting",
        2,
        color = Color(0xFFC22E28),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Pok%C3%A9mon_Fighting_Type_Icon.svg/120px-Pok%C3%A9mon_Fighting_Type_Icon.svg.png"
    ),
    Flying(
        "flying",
        3,
        color = Color(0xFFA98FF3),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/Pok%C3%A9mon_Flying_Type_Icon.svg/120px-Pok%C3%A9mon_Flying_Type_Icon.svg.png"
    ),
    Poison(
        "poison",
        4,
        color = Color(0xFFA33EA1),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Pok%C3%A9mon_Poison_Type_Icon.svg/120px-Pok%C3%A9mon_Poison_Type_Icon.svg.png"
    ),
    Ground(
        "ground",
        5,
        color = Color(0xFFE2BF65),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/Pok%C3%A9mon_Ground_Type_Icon.svg/120px-Pok%C3%A9mon_Ground_Type_Icon.svg.png"
    ),
    Rock(
        "rock",
        6,
        color = Color(0xFFB6A136),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Pok%C3%A9mon_Rock_Type_Icon.svg/120px-Pok%C3%A9mon_Rock_Type_Icon.svg.png"
    ),
    Bug(
        "bug",
        7,
        color = Color(0xFFA6B91A),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Pok%C3%A9mon_Bug_Type_Icon.svg/120px-Pok%C3%A9mon_Bug_Type_Icon.svg.png"
    ),
    Ghost(
        "ghost",
        8,
        color = Color(0xFF735797),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Pok%C3%A9mon_Ghost_Type_Icon.svg/120px-Pok%C3%A9mon_Ghost_Type_Icon.svg.png"
    ),
    Steel(
        "steel",
        9,
        color = Color(0xFFB7B7CE),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/Pok%C3%A9mon_Steel_Type_Icon.svg/120px-Pok%C3%A9mon_Steel_Type_Icon.svg.png"
    ),
    Fire(
        "fire",
        10,
        color = Color(0xFFEE8130),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Pok%C3%A9mon_Fire_Type_Icon.svg/120px-Pok%C3%A9mon_Fire_Type_Icon.svg.png"
    ),
    Water(
        "water",
        11,
        color = Color(0xFF6390F0),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Pok%C3%A9mon_Water_Type_Icon.svg/120px-Pok%C3%A9mon_Water_Type_Icon.svg.png"
    ),
    Grass(
        "grass",
        12,
        color = Color(0xFF7AC74C),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Pok%C3%A9mon_Grass_Type_Icon.svg/120px-Pok%C3%A9mon_Grass_Type_Icon.svg.png"
    ),
    Electric(
        "electric",
        13,
        color = Color(0xFFF7D02C),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Pok%C3%A9mon_Electric_Type_Icon.svg/120px-Pok%C3%A9mon_Electric_Type_Icon.svg.png"
    ),
    Psychic(
        "psychic",
        14,
        color = Color(0xFFF95587),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/Pok%C3%A9mon_Psychic_Type_Icon.svg/120px-Pok%C3%A9mon_Psychic_Type_Icon.svg.png"
    ),
    Ice(
        "ice",
        15,
        color = Color(0xFF96D9D6),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Pok%C3%A9mon_Ice_Type_Icon.svg/120px-Pok%C3%A9mon_Ice_Type_Icon.svg.png"
    ),
    Dragon(
        "dragon",
        16,
        color = Color(0xFF6F35FC),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Pok%C3%A9mon_Dragon_Type_Icon.svg/120px-Pok%C3%A9mon_Dragon_Type_Icon.svg.png"
    ),
    Dark(
        "dark",
        17,
        color = Color(0xFF705746),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Pok%C3%A9mon_Dark_Type_Icon.svg/120px-Pok%C3%A9mon_Dark_Type_Icon.svg.png"
    ),
    Fairy(
        "fairy",
        18,
        color = Color(0xFFD685AD),
        imageAddress = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Pok%C3%A9mon_Fairy_Type_Icon.svg/120px-Pok%C3%A9mon_Fairy_Type_Icon.svg.png"
    ),
    Unknown("unknown", 10001),
    Shadow("shadow", 10002),
}

fun getPokemonType(typeName: String): PokeType {
    return when (typeName) {
        "normal" -> PokeType.Normal
        "fighting" -> PokeType.Fighting
        "flying" -> PokeType.Flying
        "poison" -> PokeType.Poison
        "ground" -> PokeType.Ground
        "rock" -> PokeType.Rock
        "bug" -> PokeType.Bug
        "ghost" -> PokeType.Ghost
        "steel" -> PokeType.Steel
        "fire" -> PokeType.Fire
        "water" -> PokeType.Water
        "grass" -> PokeType.Grass
        "electric" -> PokeType.Electric
        "psychic" -> PokeType.Psychic
        "ice" -> PokeType.Ice
        "dragon" -> PokeType.Dragon
        "dark" -> PokeType.Dark
        "fairy" -> PokeType.Fairy
        "unknown" -> PokeType.Unknown
        "shadow" -> PokeType.Shadow
        else -> PokeType.Normal
    }
}
