package com.zzz.dishesapp.feature_recipes.data.util

import com.zzz.dishesapp.feature_recipes.domain.NetworkError
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import com.zzz.dishesapp.feature_recipes.domain.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body


/**
 *
 *
 * @author zyzz
*/
suspend inline fun <reified T> safeNetworkCall(
    coroutineContext : CoroutineDispatcher = Dispatchers.IO,
    crossinline block: suspend () -> HttpResponse,
) : Result<T , NetworkError> {
    return withContext(coroutineContext){
        val response = try {
            block()
        } catch (e : SerializationException) {
            e.printStackTrace()
            return@withContext Result.Error(NetworkError.SERIALIZATION_ERROR)
        }catch (e : Exception) {
            coroutineContext.ensureActive()
            e.printStackTrace()
            return@withContext Result.Error(NetworkError.ERROR_UNKNOWN)
        }

        return@withContext responseToResult(response)
    }
}

/**
 * Map response to result
 * @author zyzz
*/
suspend inline fun<reified T> responseToResult(
    response : HttpResponse
) : Result<T , NetworkError> {
    return when(response.status.value){
        in 200..299->{
            try {
                Result.Success(response.body<T>())
            } catch (_ : NoTransformationFoundException) {
                Result.Error(NetworkError.SERIALIZATION_ERROR)
            }
        }
        400->{
            Result.Error(NetworkError.BAD_REQUEST)
        }
        401->{
            Result.Error(NetworkError.UNAUTHORIZED)
        }
        403->{
            Result.Error(NetworkError.FORBIDDEN)
        }
        404->{
            Result.Error(NetworkError.NOT_FOUND)
        }
        in 400..499->{
            Result.Error(NetworkError.CLIENT)
        }
        in 500..599->{
            Result.Error(NetworkError.SERVER)
        }
        else->{
            Result.Error(NetworkError.ERROR_UNKNOWN)
        }
    }
}

