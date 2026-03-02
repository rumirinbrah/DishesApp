package com.zzz.dishesapp.feature_recipes.domain.source

import com.zzz.dishesapp.feature_recipes.domain.NetworkError
import com.zzz.dishesapp.feature_recipes.domain.Result
import com.zzz.dishesapp.feature_recipes.domain.model.Dish

interface DishesSource {

    suspend fun getDishes() : Result<List<Dish> , NetworkError>

}