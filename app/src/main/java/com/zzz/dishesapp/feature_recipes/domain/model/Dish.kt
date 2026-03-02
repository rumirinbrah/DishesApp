package com.zzz.dishesapp.feature_recipes.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dish(
    val dishId: String,
    val dishName: String,
    val imageUrl: String,
    val isVeg: Boolean,
    val isPublished: Boolean,
    @SerialName("Time")
    val time: String,
    val dishCategory: String,
    @SerialName("IngredientCategory")
    val ingredientCategory: String
)
