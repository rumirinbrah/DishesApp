package com.zzz.dishesapp.feature_recipes.domain

//marker interface
interface Error
typealias DomainError = Error

/**
 * Util
 * @author zyzz
*/
sealed interface Result<out D , out E : Error> {

    data class Success<out D>(val data: D) : Result<D , Nothing>
    data class Error<out E : DomainError>(val error: E) : Result<Nothing , E>

}

inline fun <T , E : DomainError , R> Result<T , E>.map(map: (T) -> R): Result<R , E> {
    return when (this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}
