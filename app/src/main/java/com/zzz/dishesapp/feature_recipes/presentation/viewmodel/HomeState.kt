package com.zzz.dishesapp.feature_recipes.presentation.viewmodel

import com.zzz.dishesapp.feature_recipes.domain.model.Dish
import com.zzz.dishesapp.feature_recipes.presentation.components.DishFilterTab

data class HomeState(
    val filteredDishes : List<Dish> = emptyList(),
    val filterOptions : List<String> = emptyList(),
    val selectedFilter : String = "",
)

data class FilterState(
    val filterTab : DishFilterTab = DishFilterTab.DISH ,
    val filter : String = "",
    val query : String = ""
)