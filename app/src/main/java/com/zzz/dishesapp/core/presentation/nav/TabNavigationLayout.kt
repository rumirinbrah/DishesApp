package com.zzz.dishesapp.core.presentation.nav

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zzz.dishesapp.core.presentation.HorizontalSpace
import com.zzz.dishesapp.feature_recipes.presentation.HomePage
import com.zzz.dishesapp.feature_recipes.presentation.HomeRoot

@Composable
fun TabNavigationLayout(
    modifier: Modifier = Modifier
) {
    Row(
        modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        VerticalNavBar()
        HorizontalSpace()
        HomeRoot()
    }
}