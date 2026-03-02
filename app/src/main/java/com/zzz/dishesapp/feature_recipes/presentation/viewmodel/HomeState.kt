package com.zzz.dishesapp.feature_recipes.presentation.viewmodel

import com.zzz.dishesapp.feature_recipes.domain.model.Dish
import com.zzz.dishesapp.feature_recipes.presentation.components.DishFilterTab

data class HomeState(
    val filteredDishes : List<Dish> = emptyList(),
    val currentFilter : DishFilterTab = DishFilterTab.DISH,
    val filterOptions : List<String> = emptyList(),
    val currentFilterOption : String = "",
)
