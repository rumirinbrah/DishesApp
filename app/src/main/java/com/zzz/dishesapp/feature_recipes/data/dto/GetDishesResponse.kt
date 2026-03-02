package com.zzz.dishesapp.feature_recipes.data.dto

import com.zzz.dishesapp.feature_recipes.domain.model.Dish
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.serialization.Serializable

/**
 * API Response
 * @author zyzz
 */
@Serializable
data class GetDishesResponse(
    val dishes: List<Dish>
)

/**
 * Mapper
 * @author zyzz
 */
fun GetDishesResponse.toDishList(): List<Dish> {
    return this.dishes.map { dish ->
        Dish(
            dishId = dish.dishId ,
            dishName = dish.dishName ,
            imageUrl = dish.imageUrl ,
            isVeg = dish.isVeg ,
            isPublished = dish.isPublished ,
            time = dish.time ,
            dishCategory = dish.dishCategory ,
            ingredientCategory = dish.ingredientCategory
        )
    }
}



