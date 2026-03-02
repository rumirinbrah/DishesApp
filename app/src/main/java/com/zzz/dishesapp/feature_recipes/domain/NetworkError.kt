package com.zzz.dishesapp.feature_recipes.domain

enum class NetworkError(val errorMsg : String) : Error {
    ERROR_UNKNOWN(""),
    SERIALIZATION_ERROR(""),

    SERVER(""),
    CLIENT(""),
    NOT_FOUND(""),
    FORBIDDEN(""),
    UNAUTHORIZED(""),
    BAD_REQUEST(""),
}