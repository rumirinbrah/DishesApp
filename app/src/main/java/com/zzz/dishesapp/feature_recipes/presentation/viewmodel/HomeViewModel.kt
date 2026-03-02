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

    private var dishes : List<Dish> = emptyList()

//    private val _filterState = MutableStateFlow(FilterState())

    //    val homeState : StateFlow<HomeState> = combine(
//        dishes ,
//        _filterState
//    ){ dishes , filterState->
//
//        //-----FILTER DISHES------
//        val filtered = filterDishes(
//            dishes ,
//            filterState.filterTab ,
//            filterState.filter ,
//            filterState.query
//        )
//        val options = getOtherFilterOptions(filterState.filterTab)
//
//        HomeState(
//            filtered ,
//            options,
//            selectedFilter = filterState.filter,
//            query = filterState.query
//        )
//    }.onStart {
//        getDishes()
//    }.stateIn(
//        viewModelScope ,
//        SharingStarted.WhileSubscribed(5000L) ,
//        HomeState()
//    )

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState
        .onStart {
            getDishes()
        }.stateIn(
            viewModelScope ,
            SharingStarted.WhileSubscribed(5000L) ,
            _homeState.value
        )


    fun onAction(
        action: HomeAction
    ) {
        when (action) {
            is HomeAction.FilterTabChange -> {
                onTabFilterChange(action.tab)
            }

            is HomeAction.OnFilterOptionChange -> {
                onFilterOptionChange(action.filter)
            }

            is HomeAction.OnQueryChange -> {
                onQueryChange(action.query)
            }

            HomeAction.Retry->{
                retry()
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
        filter: String ,
        searchQuery: String
    ): List<Dish> {
        return dishes.filter {
            val tabsFilter = when (tab) {
                DishFilterTab.DISH -> {
                    filter.isBlank() || it.dishCategory.equals(filter , ignoreCase = true)
                }

                DishFilterTab.INGREDIENT -> {
                    filter.isBlank() || it.ingredientCategory.equals(filter , ignoreCase = true)
                }
            }
            val searchFilter = it.dishName.contains(searchQuery , true)
            tabsFilter && searchFilter
        }
    }

    private fun onQueryChange(text: String) {
        viewModelScope.launch {
            log {
                "query change : $text"
            }
            val searchResults = dishes.filter {
                it.dishName.contains(text,true) ||
                        it.dishCategory.contains(text,true) ||
                        it.ingredientCategory.contains(text,true)
            }
            _homeState.update {
                it.copy(
                    query = text,
                    filteredDishes = searchResults
                )
            }


        }
    }

    private fun getDishes() {
        viewModelScope.launch {
            _homeState.update {
                it.copy(
                    loading = true
                )
            }
            when (val result = dishesSource.getDishes()) {
                is Result.Error -> {
                    log {
                        "getDishes : Error ${result.error}"
                    }
                    _homeState.update {
                        it.copy(
                            loading = false,
                            errorMsg = result.error.errorMsg
                        )
                    }
                }

                is Result.Success -> {
                    log {
                        "getDishes : Success! ${result.data}"
                    }
                    dishes = result.data
                    val filtered = filterDishes(
                        dishes ,
                        _homeState.value.filterTab ,
                        _homeState.value.filter ,
                        _homeState.value.query
                    )
                    val options = getOtherFilterOptions(_homeState.value.filterTab)
                    _homeState.update {
                        it.copy(
                            filteredDishes = filtered,
                            filterOptions = options,
                            loading = false
                        )
                    }

//                    onTabFilterChange(_filterTab.value)
                }
            }
        }

    }

    private fun onTabFilterChange(
        filterTab: DishFilterTab
    ) {
        log {
            "onTabFilterChange : $filterTab"
        }
        viewModelScope.launch {
            val options = getOtherFilterOptions(filterTab)
            _homeState.update {
                it.copy(
                    filterOptions = options,
                    filterTab = filterTab ,
                    filter = "",
                )
            }
        }
    }

    private fun onFilterOptionChange(filter: String) {
        viewModelScope.launch {
            log {
                "Filter is $filter"
            }
            val filtered = filterDishes(
                dishes ,
                _homeState.value.filterTab ,
                filter ,
                _homeState.value.query
            )
            _homeState.update {
                it.copy(
                    filter = filter,
                    filteredDishes = filtered
                )
            }
        }
    }

    /**
     * Get all filterable options for the selected TAB
     * @author zyzz
     */
    private fun getOtherFilterOptions(filterTab: DishFilterTab): List<String> {
        log {
            "Filtering options for ${filterTab}"
        }
        val filters = when (filterTab) {
            DishFilterTab.DISH -> {
                dishes.map {
                    it.dishCategory
                }.distinct()
            }

            DishFilterTab.INGREDIENT -> {
                dishes.map {
                    it.ingredientCategory
                }.distinct()
            }
        }
        return filters
    }

    private fun retry(){
        _homeState.update {
            it.copy(errorMsg = null)
        }
        getDishes()
    }


    private fun log(msg: () -> String) {
        if (loggingEnabled) {
            Log.d("HomeViewModel : " , msg())
        }
    }


}