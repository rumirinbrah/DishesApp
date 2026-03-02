package com.zzz.dishesapp.feature_recipes.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zzz.dishesapp.R
import com.zzz.dishesapp.core.presentation.RectIconButton
import com.zzz.dishesapp.core.presentation.RectTextButton
import com.zzz.dishesapp.core.presentation.SearchBar

/**
 * Search and other options
 * @author zyzz
*/
@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchBar(
            query = "" ,
            onQueryChange = {

            },
            modifier = Modifier.weight(1f)
//                .height(42.dp),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            RectIconButton(
                icon = R.drawable.wifi,
                onClick = {}
            )
            RectIconButton(
                icon = R.drawable.power_off,
                onClick = {}
            )
            RectTextButton(
                text = "A",
                onClick = {}
            )
        }
    }
}