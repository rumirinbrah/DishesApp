package com.zzz.dishesapp.core.presentation

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zzz.dishesapp.R
import com.zzz.dishesapp.ui.theme.background

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query : String,
    onQueryChange : (String)->Unit,
    enabled : Boolean = true
) {
    NormalTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = "Look up dishes...",
        enabled = enabled,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        leadingIcon = R.drawable.search_icon,
        background = background
    )
}