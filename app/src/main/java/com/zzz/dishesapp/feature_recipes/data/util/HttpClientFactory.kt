package com.zzz.dishesapp.feature_recipes.data.util

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


/**
 * For http calls with ktor
 *
 * @author zyzz
*/
object HttpClientFactory {
    fun create(
        engine: HttpClientEngine
    ) : HttpClient{
        return HttpClient(engine){
            expectSuccess = false

            //-----------SERIALIZATION-----------
            install(ContentNegotiation){
                json(
                    json= Json{
                        ignoreUnknownKeys = true
//                        this.namingStrategy = JsonNamingStrategy.SnakeCase
                    }
                )
            }
            //-----------TIMEOUT-----------
            install(HttpTimeout){
                requestTimeoutMillis = 20_000L
                socketTimeoutMillis = 20_000L
            }

        }
    }
}


