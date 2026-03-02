package com.zzz.dishesapp.core.presentation.nav

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.zzz.dishesapp.core.presentation.HorizontalSpace
import com.zzz.dishesapp.feature_recipes.presentation.HomePage
import com.zzz.dishesapp.feature_recipes.presentation.HomeRoot

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun Navigation(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val windowClass = calculateWindowSizeClass(context as Activity)
    when(windowClass.widthSizeClass){
        WindowWidthSizeClass.Compact->{
            PhoneNavigationLayout(modifier)
        }
        else -> {
            TabNavigationLayout(modifier)
        }
    }
}

@Composable
fun TabNavigationLayout(
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        VerticalNavBar()
        HorizontalSpace()
        HomeRoot(
            isPhone = false
        )
    }
}

@Composable
fun PhoneNavigationLayout(
    modifier: Modifier = Modifier
) {
    var navBarHeight by remember() {
        mutableStateOf(0.dp)
    }
    val density = LocalDensity.current

    Box(
        Modifier.fillMaxSize()
    ){
        Column(
            modifier
                .padding(bottom = navBarHeight)
        ) {
            HomeRoot(
                isPhone = true
            )
        }
        HorizontalNavBar(
            Modifier.align(Alignment.BottomCenter)
                .onGloballyPositioned{
                    with(density){
                        navBarHeight = it.size.height.toDp()
                    }
                }
        )
    }
}
