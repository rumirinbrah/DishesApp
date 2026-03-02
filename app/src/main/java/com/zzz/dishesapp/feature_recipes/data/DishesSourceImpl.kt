package com.zzz.dishesapp.feature_recipes.data

import com.zzz.dishesapp.feature_recipes.data.dto.GetDishesResponse
import com.zzz.dishesapp.feature_recipes.data.dto.toDishList
import com.zzz.dishesapp.feature_recipes.data.util.safeNetworkCall
import com.zzz.dishesapp.feature_recipes.domain.NetworkError
import com.zzz.dishesapp.feature_recipes.domain.Result
import com.zzz.dishesapp.feature_recipes.domain.map
import com.zzz.dishesapp.feature_recipes.domain.model.Dish
import com.zzz.dishesapp.feature_recipes.domain.source.DishesSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class DishesSourceImpl(
    private val client : HttpClient
) : DishesSource {
    private val apiUrl = "https://fls8oe8xp7.execute-api.ap-south-1.amazonaws.com/dev/nosh-assignment"

    override suspend fun getDishes(): Result<List<Dish>, NetworkError> {
        return safeNetworkCall<List<Dish>> {
            client.get(apiUrl)
        }
//            .map {response ->
//            response.toDishList()
//        }
    }
}