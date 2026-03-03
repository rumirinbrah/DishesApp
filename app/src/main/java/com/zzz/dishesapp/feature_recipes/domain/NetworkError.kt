package com.zzz.dishesapp.feature_recipes.domain

//Remote errors
enum class NetworkError(val errorMsg : String) : Error {
    ERROR_UNKNOWN("Oops! An unknown error occurred!"),
    SERIALIZATION_ERROR("Something went wrong"),

    SERVER("Something went wrong"),
    CLIENT("Something went wrong"),
    NOT_FOUND("Something went wrong"),
    FORBIDDEN("Something went wrong"),
    UNAUTHORIZED("Something went wrong"),
    BAD_REQUEST("Bad request"),
}