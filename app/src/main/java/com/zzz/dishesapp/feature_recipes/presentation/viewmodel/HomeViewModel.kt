package com.zzz.dishesapp.feature_recipes.presentation.viewmodel

import android.util.Log
import android.widget.Filterable
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

class HomeViewModel(
    private val dishesSource: DishesSource
) : ViewModel() {

    private val loggingEnabled = true

    private var dishes = MutableStateFlow<List<Dish>>(emptyList())

    private val _filterTab = MutableStateFlow(DishFilterTab.DISH)
    private val _filterOption = MutableStateFlow("")

    val tempState : StateFlow<HomeState> = combine(dishes,_filterTab,_filterOption){ dishes, tab , filter->
        val filtered = dishes.filter {
            when(tab){
                DishFilterTab.DISH -> {
                    filter.isBlank() ||it.dishCategory.equals(filter, ignoreCase = true)
                }
                DishFilterTab.INGREDIENT -> {
                    filter.isBlank() ||it.ingredientCategory.equals(filter, ignoreCase = true)
                }
            }
        }
        val options = getOtherFilterOptions(tab)
        HomeState(
            filtered ,
            options,
            selectedFilter = filter
        )
    }.onStart {
        getDishes()
    }.stateIn(
        viewModelScope ,
        SharingStarted.WhileSubscribed(5000L) ,
        HomeState()
    )

    private val _state = MutableStateFlow(HomeState())
    val state = _state
        .onStart {
            getDishes()
        }
        .stateIn(
            viewModelScope ,
            SharingStarted.WhileSubscribed(5000L) ,
            _state.value
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
                    _state.update {
                        it.copy(
                            filteredDishes = result.data
                        )
                    }
                    onTabFilterChange(_filterTab.value)
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
//            val filters = getOtherFilterOptions(filterTab)
//            _state.update {
//                it.copy(
//                    currentFilter = filterTab,
//                    filterOptions = filters
//                )
//            }
            _filterTab.update {
                filterTab
            }
            _filterOption.update {
                ""
            }
        }
    }
    private fun onFilterOptionChange(filter : String){
        viewModelScope.launch {
            _filterOption.update {
                filter
            }
        }
    }

    private fun getOtherFilterOptions(filterTab: DishFilterTab) : List<String>{
        val values = _state.value
        log {
            "Filterings options for ${_filterTab.value}"
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
            Log.d("MediaFileManager : " , msg())
        }
    }


}