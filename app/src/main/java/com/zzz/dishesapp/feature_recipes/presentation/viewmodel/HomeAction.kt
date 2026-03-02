package com.zzz.dishesapp.feature_recipes.presentation.viewmodel

import com.zzz.dishesapp.feature_recipes.presentation.components.DishFilterTab

sealed class HomeAction {
    data class FilterTabChange(val tab: DishFilterTab) : HomeAction()
    data class OnFilterOptionChange(val filter : String) : HomeAction()
    data class OnQueryChange(val query : String) : HomeAction()

    data object Retry : HomeAction()

}