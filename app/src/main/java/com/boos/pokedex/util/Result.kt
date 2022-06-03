package com.boos.pokedex.util

sealed class Result<T>(val resultData: T? = null, resultMessage: String? = null) {
    data class Success<T>(val data: T): Result<T>(data)
    data class Error<T>(val data: T? = null, val message: String): Result<T>(data, message)
    data class Loading<T>(val isLoading: Boolean = true, val data: T? = null): Result<T>(data)
}