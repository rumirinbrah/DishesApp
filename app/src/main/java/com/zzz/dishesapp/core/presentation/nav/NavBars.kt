package com.zzz.dishesapp.core.presentation.nav

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.zzz.dishesapp.R
import com.zzz.dishesapp.core.presentation.VerticalSpace
import com.zzz.dishesapp.ui.theme.orange

/**
 * For tab
 * @author zyzz
*/
@Composable
fun VerticalNavBar(
    modifier: Modifier = Modifier
) {
    var current by remember() {
        mutableStateOf<Screen>(Screen.Home)
    }

    Column(
        modifier
            .padding(horizontal = 8.dp , vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavItem(
            icon = R.drawable.home,
            title = "Home",
            selected = current == Screen.Home,
            onClick = {
                current = Screen.Home
            }
        )
        NavItem(
            icon = R.drawable.microwave,
            title = "Reheat",
            selected = current == Screen.Reheat,
            onClick = {
                current = Screen.Reheat
            }
        )
        NavItem(
            icon = R.drawable.format_list_bulleted,
            title = "Preset",
            selected = current == Screen.Preset,
            onClick = {
                current = Screen.Preset
            }
        )
        NavItem(
            icon = R.drawable.chef_hat,
            title = "Copilot",
            selected = current == Screen.Copilot,
            onClick = {
                current = Screen.Copilot
            }
        )
        NavItem(
            icon = R.drawable.soup_kitchen,
            title = "Flavor",
            selected = current == Screen.Flavor,
            onClick = {
                current = Screen.Flavor
            }
        )

        HorizontalDivider(
            Modifier.width(25.dp)
        )

        NavItem(
            icon = R.drawable.tv_gen,
            title = "Care Mode",
            selected = current == Screen.CareMode,
            onClick = {
                current = Screen.CareMode
            }
        )
        NavItem(
            icon = R.drawable.support,
            title = "Support",
            selected = current == Screen.Support,
            onClick = {
                current = Screen.Support
            }
        )
    }
}

/**
 * For phone
 * @author zyzz
*/
@Composable
fun HorizontalNavBar(
    modifier: Modifier = Modifier
) {
    var current by remember {
        mutableStateOf<Screen>(Screen.Home)
    }

    Box(
        modifier
            .background(
                Color.White ,
                MaterialTheme.shapes.large
            )
            .innerShadow(
                shape = MaterialTheme.shapes.large ,
                shadow = Shadow(
                    radius = 5.dp ,
                    color = MaterialTheme.colorScheme.onBackground.copy(0.05f) ,
                    spread = 1.dp ,
                    offset = DpOffset(x = 1.dp , y = 1.dp)
                )
            )
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ){
        Row(
            Modifier
                .fillMaxWidth(0.95f)
                .horizontalScroll(rememberScrollState()) ,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem(
                icon = R.drawable.home,
                title = "Home",
                selected = current == Screen.Home,
                onClick = {
                    current = Screen.Home
                }
            )
            NavItem(
                icon = R.drawable.microwave,
                title = "Reheat",
                selected = current == Screen.Reheat,
                onClick = {
                    current = Screen.Reheat
                }
            )
            NavItem(
                icon = R.drawable.format_list_bulleted,
                title = "Preset",
                selected = current == Screen.Preset,
                onClick = {
                    current = Screen.Preset
                }
            )
            NavItem(
                icon = R.drawable.chef_hat,
                title = "Copilot",
                selected = current == Screen.Copilot,
                onClick = {
                    current = Screen.Copilot
                }
            )
            NavItem(
                icon = R.drawable.soup_kitchen,
                title = "Flavor",
                selected = current == Screen.Flavor,
                onClick = {
                    current = Screen.Flavor
                }
            )

            NavItem(
                icon = R.drawable.tv_gen,
                title = "Care Mode",
                selected = current == Screen.CareMode,
                onClick = {
                    current = Screen.CareMode
                }
            )
            NavItem(
                icon = R.drawable.support,
                title = "Support",
                selected = current == Screen.Support,
                onClick = {
                    current = Screen.Support
                }
            )
        }
    }

}

@Composable
private fun NavItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon : Int,
    title : String,
    onClick : ()->Unit,
    selected : Boolean = false,
    iconSize : Dp = 25.dp,
    style : TextStyle = MaterialTheme.typography.bodySmall,
    tint  : Color = Color.Gray
) {
    val iconTint = remember(selected) {
        if(selected){
            orange
        }else{
            tint
        }
    }

    Column(
        modifier
            .clickable(
                interactionSource = null,
                indication = null,
                onClick = {
                    if(!selected){
                        onClick()
                    }
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = title,
            modifier = Modifier.size(iconSize),
            tint = iconTint
        )
        VerticalSpace(5.dp)
        Text(
            title,
            style = style,
            color = iconTint
        )
    }
}

@Preview
@Composable
private fun NavBarPrev() {

}