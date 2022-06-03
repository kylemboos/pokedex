package com.boos.pokedex.util

fun String.withPokedexLeadingZeros(): String {
    val numAsInt = toInt()
    val leadingZeros = if (numAsInt < 10) {
        "00"
    } else if (numAsInt < 100) {
        "0"
    } else {
        ""
    }
    return "$leadingZeros$this"
}