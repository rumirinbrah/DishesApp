package com.zzz.dishesapp.feature_recipes.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zzz.dishesapp.ui.theme.orange

@Composable
fun FilterOptionChip(
    modifier: Modifier = Modifier ,
    text: String ,
    onClick: (filter : String) -> Unit ,
    selected: Boolean = false ,
    style: TextStyle = MaterialTheme.typography.titleMedium
) {
    val indicatorColor by animateColorAsState(
        targetValue = if(selected){
            orange
        }else{
            Color.Gray
        }
    )

    Box(
        modifier
            .drawBehind {
//                if (selected) {
//                    drawRoundRect(
//                        color = orange.copy(0.1f) ,
//                        cornerRadius = CornerRadius(
//                            x = 30f ,
//                            y = 30f
//                        ) ,
//                    )
//                    drawRoundRect(
//                        color = orange ,
//                        cornerRadius = CornerRadius(
//                            x = 30f ,
//                            y = 30f
//                        ) ,
//                        style = Stroke(4f)
//                    )
//                }
            }
            .clickable {
                if (!selected) {
                    onClick(text)
                }else{
                    onClick("")
                }
            }
            .padding(horizontal = 12.dp , vertical = 8.dp) ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            color = indicatorColor,
            fontWeight = FontWeight.Medium,
            style =style
        )
    }
}