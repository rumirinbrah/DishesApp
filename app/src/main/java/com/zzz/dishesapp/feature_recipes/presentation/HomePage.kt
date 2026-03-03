package com.zzz.dishesapp.feature_recipes.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz.dishesapp.core.presentation.VerticalSpace
import com.zzz.dishesapp.feature_recipes.domain.model.Dish
import com.zzz.dishesapp.feature_recipes.presentation.components.DishFilterTabRow
import com.zzz.dishesapp.feature_recipes.presentation.components.DishItem
import com.zzz.dishesapp.feature_recipes.presentation.components.ErrorComponent
import com.zzz.dishesapp.feature_recipes.presentation.components.FilterOptionChip
import com.zzz.dishesapp.feature_recipes.presentation.components.HomeTopBar
import com.zzz.dishesapp.feature_recipes.presentation.viewmodel.HomeAction
import com.zzz.dishesapp.feature_recipes.presentation.viewmodel.HomeState
import com.zzz.dishesapp.feature_recipes.presentation.viewmodel.HomeViewModel
import com.zzz.dishesapp.ui.theme.orange
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoot(
    modifier: Modifier = Modifier ,
    isPhone: Boolean
) {
    val homeViewModel = koinViewModel<HomeViewModel>()
    val state by homeViewModel.homeState.collectAsStateWithLifecycle()

    when{
        state.errorMsg!=null->{
            ErrorComponent(
                message = state.errorMsg ?: "",
                onRetry = {
                    homeViewModel.onAction(HomeAction.Retry)
                }
            )
        }
        else->{
            HomePage(
                modifier ,
                state = state ,
                onAction = {
                    homeViewModel.onAction(it)
                } ,
                isPhone = isPhone
            )
        }
    }

}

@Composable
fun HomePage(
    modifier: Modifier = Modifier ,
    state: HomeState = HomeState() ,
    onAction: (action: HomeAction) -> Unit ,
    isPhone: Boolean
) {

    Box(
        Modifier.fillMaxSize()
    ) {

        Column(
            modifier
                .fillMaxWidth()
                .padding(8.dp) ,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HomeTopBar(
                query = state.query ,
                onQueryChange = {
                    onAction(HomeAction.OnQueryChange(it))
                }
            )
            //-------LOADING--------
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                visible = state.loading
            ) {
                CircularProgressIndicator(
                    Modifier.size(30.dp),
                    color = orange,
                    strokeWidth = 2.dp
                )
            }

            //-------FILTER ROW--------
            DishFilterTabRow(
                Modifier.align(Alignment.CenterHorizontally) ,
                onTabChange = {
                    onAction(HomeAction.FilterTabChange(it))
                }
            )

            //-------CHIPS------
            VerticalSpace(5.dp)
            FilterOptions(
                Modifier
                    .align(Alignment.CenterHorizontally),
                options = state.filterOptions,
                onClick = {
                    onAction(HomeAction.OnFilterOptionChange(it))
                },
                currentFilter = state.filter
            )

            //-------DISHES--------
            if (isPhone) {
                DishesGrid(
                    dishes = state.filteredDishes
                )
            } else {
                AnimatedContent(state.filteredDishes) { dishes ->
                    DishesFlowContainer(
                        Modifier ,
                        dishes = dishes
                    )
                }
            }


        }
    }
}

@Composable
private fun FilterOptions(
    modifier: Modifier = Modifier,
    options : List<String>,
    onClick : (option : String) ->Unit,
    currentFilter : String,
) {
    Column(
        modifier ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Filter options" ,
            fontWeight = FontWeight.Bold
        )

        VerticalSpace(5.dp)
        HorizontalDivider(
            Modifier.width(70.dp)
        )

        AnimatedContent(
            options
        ) { filters ->
            LazyRow(
                Modifier
                    .padding(vertical = 8.dp , horizontal = 26.dp) ,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filters) { filter ->
                    FilterOptionChip(
                        text = filter ,
                        onClick = {
                            onClick(it)
                        } ,
                        selected = currentFilter == filter
                    )
                }
            }
        }

    }
}

@Composable
private fun DishesFlowContainer(
    modifier: Modifier = Modifier ,
    dishes: List<Dish>
) {

    VerticalSpace()
    FlowRow(
        modifier.fillMaxWidth() ,
        //                    .background(Color.Gray)
        //                    .padding(horizontal = 16.dp)
        horizontalArrangement = Arrangement.spacedBy(8.dp) ,
        verticalArrangement = Arrangement.spacedBy(16.dp) ,
    ) {
        dishes.onEach { dish ->
            DishItem(
                dish = dish
            )
        }
    }

}

@Composable
private fun DishesGrid(
    modifier: Modifier = Modifier ,
    dishes: List<Dish> ,
    maxItems: Int = 2 ,
) {
    val state = rememberLazyGridState()

    LaunchedEffect(dishes) {
        state.scrollToItem(0)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(maxItems) ,
        modifier.fillMaxWidth() ,
        horizontalArrangement = Arrangement.spacedBy(8.dp) ,
        verticalArrangement = Arrangement.spacedBy(16.dp) ,
        state = state
    ) {
        items(
            dishes ,
            key = {
                it.dishId
            }
        ) { dish ->
            DishItem(
                dish = dish ,
                modifier = Modifier.animateItem()
            )
        }
    }

}

