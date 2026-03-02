package com.zzz.dishesapp.feature_recipes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.dishesapp.feature_recipes.domain.Result
import com.zzz.dishesapp.feature_recipes.domain.model.Dish
import com.zzz.dishesapp.feature_recipes.domain.source.DishesSource
import com.zzz.dishesapp.feature_recipes.presentation.components.DishFilterTab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.filter

class HomeViewModel(
    private val dishesSource: DishesSource
) : ViewModel() {

    private val loggingEnabled = true

    private var dishes = MutableStateFlow<List<Dish>>(emptyList())

    private val _filterState = MutableStateFlow(FilterState())

    val homeState : StateFlow<HomeState> = combine(
        dishes ,
        _filterState
    ){ dishes , filterState->

        //-----FILTER DISHES------
        val filtered = filterDishes(
            dishes ,
            filterState.filterTab ,
            filterState.filter ,
            filterState.query
        )
        val options = getOtherFilterOptions(filterState.filterTab)

        HomeState(
            filtered ,
            options,
            selectedFilter = filterState.filter,
            query = filterState.query
        )
    }.onStart {
        getDishes()
    }.stateIn(
        viewModelScope ,
        SharingStarted.WhileSubscribed(5000L) ,
        HomeState()
    )



    fun onAction(
        action: HomeAction
    ){
        when(action){
            is HomeAction.FilterTabChange -> {
                onTabFilterChange(action.tab)
            }

            is HomeAction.OnFilterOptionChange -> {
                onFilterOptionChange(action.filter)
            }

            is HomeAction.OnQueryChange ->{
                onQueryChange(action.query)
            }
        }
    }

    /**
     * Filter on the basis of tab, selected option & search query
     * @author zyzz
    */
    private fun filterDishes(
        dishes: List<Dish> ,
        tab: DishFilterTab ,
        filter: String,
        searchQuery : String
    ) : List<Dish>{
        return dishes.filter {
            val tabsFilter = when(tab){
                DishFilterTab.DISH -> {
                    filter.isBlank() ||it.dishCategory.equals(filter, ignoreCase = true)
                }
                DishFilterTab.INGREDIENT -> {
                    filter.isBlank() ||it.ingredientCategory.equals(filter, ignoreCase = true)
                }
            }
            val searchFilter = it.dishName.contains(searchQuery,true)
            tabsFilter && searchFilter
        }
    }

    private fun onQueryChange(text : String) {
        viewModelScope.launch {
            log {
                "query change : $text"
            }
            _filterState.update {
                it.copy(query = text)
            }

        }
    }

    private fun getDishes() {
        viewModelScope.launch {
            when (val result = dishesSource.getDishes()) {
                is Result.Error -> {
                    log {
                        "getDishes : Error ${result.error}"
                    }
                }

                is Result.Success -> {
                    log {
                        "getDishes : Success! ${result.data}"
                    }
                    dishes .update {
                        result.data
                    }

//                    onTabFilterChange(_filterTab.value)
                }
            }
        }

    }

    private fun onTabFilterChange(
        filterTab: DishFilterTab
    ){
        log {
            "onTabFilterChange : $filterTab"
        }
        viewModelScope.launch {

            _filterState.update {
                it.copy(
                    filterTab = filterTab,
                    filter = ""
                )
            }
        }
    }
    private fun onFilterOptionChange(filter : String){
        viewModelScope.launch {
            _filterState.update {
                it.copy(filter = filter)
            }
        }
    }

    /**
     * Get all filterable options for the selected TAB
     * @author zyzz
    */
    private fun getOtherFilterOptions(filterTab: DishFilterTab) : List<String>{
        log {
            "Filtering options for ${filterTab}"
        }
        val filters = when(filterTab){
            DishFilterTab.DISH -> {
                dishes.value.map {
                    it.dishCategory
                }.distinct()
            }
            DishFilterTab.INGREDIENT -> {
                dishes.value.map {
                    it.ingredientCategory
                }.distinct()
            }
        }
        return filters
    }


    private fun log(msg: () -> String) {
        if (loggingEnabled) {
            Log.d("HomeViewModel : " , msg())
        }
    }


}