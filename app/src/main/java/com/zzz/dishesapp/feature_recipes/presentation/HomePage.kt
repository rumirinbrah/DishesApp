package com.zzz.dishesapp.feature_recipes.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz.dishesapp.core.presentation.VerticalSpace
import com.zzz.dishesapp.feature_recipes.presentation.components.DishFilterTabRow
import com.zzz.dishesapp.feature_recipes.presentation.components.DishItem
import com.zzz.dishesapp.feature_recipes.presentation.components.FilterOptionChip
import com.zzz.dishesapp.feature_recipes.presentation.components.HomeTopBar
import com.zzz.dishesapp.feature_recipes.presentation.viewmodel.HomeAction
import com.zzz.dishesapp.feature_recipes.presentation.viewmodel.HomeState
import com.zzz.dishesapp.feature_recipes.presentation.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoot(
    modifier: Modifier = Modifier
) {
    val homeViewModel = koinViewModel<HomeViewModel>()
    val state by homeViewModel.state.collectAsStateWithLifecycle()

    HomePage(
        modifier ,
        state = state,
        onAction = {
            homeViewModel.onAction(it)
        }
    )
}

@Composable
fun HomePage(
    modifier: Modifier = Modifier ,
    state: HomeState = HomeState() ,
    onAction : (action : HomeAction)->Unit
) {

    Box(
        Modifier.fillMaxSize()
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .padding(8.dp)
            ,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HomeTopBar()

//            VerticalSpace(10.dp)

            //-------FILTER ROW--------
            DishFilterTabRow(
                Modifier.align(Alignment.CenterHorizontally),
                onTabChange = {
                    onAction(HomeAction.FilterTabChange(it))
                }
            )

            VerticalSpace(5.dp)
            //-------CHIPS------
            Column(
                Modifier
                    .align(Alignment.CenterHorizontally) ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Filter options",
                    fontWeight = FontWeight.Bold
                )

                AnimatedContent(
                    state.filterOptions
                ) {filters->
                    LazyRow(
                        Modifier
                            .padding(vertical = 8.dp , horizontal = 26.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(filters){
                            FilterOptionChip(
                                text = it ,
                                onClick = {

                                } ,
                                selected = true
                            )
                        }
                    }
                }
                HorizontalDivider(
                    Modifier.width(70.dp)
                )
            }

            VerticalSpace()
            //-------DISHES--------
            FlowRow(
                Modifier.fillMaxWidth()
//                    .background(Color.Gray)
//                    .padding(horizontal = 16.dp)
                ,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                state.filteredDishes.onEach { dish ->
                    DishItem(
                        dish = dish
                    )
                }
//                item {
//                    HorizontalSpace()
//                }
//                items(
//                    state.filteredDishes,
//                    key = {
//                        it.dishId
//                    }
//                ) {dish->
//                    DishItem(
//                        dish = dish
//                    )
//                }
            }

        }
    }
}