package com.zzz.dishesapp.core.presentation.nav

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zzz.dishesapp.R
import com.zzz.dishesapp.core.presentation.VerticalSpace
import com.zzz.dishesapp.ui.theme.orange

@Composable
fun VerticalNavBar(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .padding(horizontal = 8.dp , vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavItem(
            icon = R.drawable.home,
            title = "Home",
            tint = orange
        )
        NavItem(
            icon = R.drawable.microwave,
            title = "Reheat",
        )
        NavItem(
            icon = R.drawable.format_list_bulleted,
            title = "Preset",
        )
        NavItem(
            icon = R.drawable.chef_hat,
            title = "Copilot",
        )
        NavItem(
            icon = R.drawable.soup_kitchen,
            title = "Flavor",
        )

        HorizontalDivider(
            Modifier.width(25.dp)
        )

        NavItem(
            icon = R.drawable.tv_gen,
            title = "Care Mode",
        )
        NavItem(
            icon = R.drawable.support,
            title = "Support",
        )
    }
}

@Composable
private fun NavItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon : Int,
    title : String,
    iconSize : Dp = 25.dp,
    style : TextStyle = MaterialTheme.typography.bodySmall,
    tint  : Color = Color.Gray
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = title,
            modifier = Modifier.size(iconSize),
            tint = tint
        )
        VerticalSpace(5.dp)
        Text(
            title,
            style = style,
            color = tint
        )
    }
}

@Preview
@Composable
private fun NavBarPrev() {

}