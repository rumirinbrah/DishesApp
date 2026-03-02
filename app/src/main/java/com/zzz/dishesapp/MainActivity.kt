package com.zzz.dishesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zzz.dishesapp.core.presentation.nav.Navigation
import com.zzz.dishesapp.feature_recipes.presentation.components.DishFilterTabRow
import com.zzz.dishesapp.ui.theme.DishesAppTheme
import com.zzz.dishesapp.ui.theme.background

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DishesAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(
                        Modifier.fillMaxSize()
                            .background(background)
                            .padding(innerPadding) ,
//                        contentAlignment = Alignment.Center
                    ){
                        Navigation()
//                        ErrorState(
//                            message = "An unknown error occurred",
//                            onRetry = {}
//                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String , modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!" ,
        modifier = modifier
    )
}

@Preview(
    showBackground = true,
    widthDp = 600,
    heightDp = 960
)
@Composable
fun GreetingPreview() {
    DishesAppTheme {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            DishFilterTabRow(
                onTabChange = {tab->

                }
            )
        }
    }
}