package com.zzz.dishesapp.feature_recipes.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zzz.dishesapp.core.presentation.TabOption
import com.zzz.dishesapp.ui.theme.lightGray

@Composable
fun DishFilterTabRow(
    modifier: Modifier = Modifier,
    onTabChange : (DishFilterTab)->Unit
) {
    var current by remember() {
        mutableStateOf(DishFilterTab.DISH)
    }

    LaunchedEffect(current) {
        onTabChange(current)
    }

    Row(
        modifier.clip(MaterialTheme.shapes.large)
            .background(Color.Gray.copy(0.05f))
            .padding(2.dp)
    ) {
        TabOption(
            text = "Dish",
            selected = current == DishFilterTab.DISH,
            onClick = {
                current = DishFilterTab.DISH
            }
        )
        TabOption(
            text = "Ingredients",
            selected = current == DishFilterTab.INGREDIENT,
            onClick = {
                current = DishFilterTab.INGREDIENT
            }
        )
    }
}
enum class DishFilterTab{
    DISH,
    INGREDIENT
}