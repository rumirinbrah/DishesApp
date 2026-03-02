package com.zzz.dishesapp.core.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RectIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon : Int,
    onClick : ()->Unit,
    iconSize : Dp = 25.dp,
) {
    Box(
        modifier
            .size(42.dp)
            .border(
                1.dp ,
                Color.Gray ,
                MaterialTheme.shapes.medium
            )
            .clickable(
                onClick = onClick
            )
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(iconSize)
        )
    }
}
@Composable
fun RectTextButton(
    modifier: Modifier = Modifier,
    text : String,
    onClick : ()->Unit,
) {
    Box(
        modifier
            .size(42.dp)
            .border(
                1.dp ,
                Color.Gray ,
                MaterialTheme.shapes.medium
            )
            .clickable(
                onClick = onClick
            )
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}