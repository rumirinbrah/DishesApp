package com.zzz.dishesapp.feature_recipes.presentation.components

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.zzz.dishesapp.R
import com.zzz.dishesapp.core.presentation.ImageComponent
import com.zzz.dishesapp.core.presentation.VerticalSpace
import com.zzz.dishesapp.feature_recipes.domain.model.Dish
import com.zzz.dishesapp.ui.theme.background
import com.zzz.dishesapp.ui.theme.green
import com.zzz.dishesapp.ui.theme.lightGray
import com.zzz.dishesapp.ui.theme.red

@Composable
fun DishItem(
    modifier: Modifier = Modifier,
    dish : Dish,
    shape : androidx.compose.ui.graphics.Shape = MaterialTheme.shapes.medium
) {
    Column(
        modifier
            .widthIn(max = 200.dp)
            .dropShadow(
                shape = shape ,
                shadow = Shadow(
                    radius = 5.dp ,
                    color = MaterialTheme.colorScheme.onBackground.copy(0.2f) ,
                    spread = 1.dp ,
                    offset = DpOffset(x = 1.dp , y = 1.dp)
                )
            )
            .clip(shape)
            .background(background)
            .padding(horizontal = 8.dp , vertical = 12.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //-----CATEGORY AND FAV------
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.veg_non_veg),
                contentDescription = "veg/nonveg",
                tint = dish.isVeg.vegNonVegTint()
            )
            Icon(
                painter = painterResource(R.drawable.favorite),
                contentDescription = "favourite"
            )
        }
        ImageComponent(
            modifier = Modifier.clip(CircleShape)
                .size(120.dp),
            url = dish.imageUrl
        )
        VerticalSpace()

        Text(
            dish.dishName,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        VerticalSpace(10.dp)
        //time
        Row() {
            Icon(
                painter = painterResource(R.drawable.soup_kitchen),
                contentDescription = "Time",
                modifier = Modifier.size(18.dp),
                tint = Color.Gray
            )
            Text(
                "${dish.time} Min",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}
private fun Boolean.vegNonVegTint() : Color{
    return if(true){
        green
    }else{
        red
    }
}

