package com.zzz.dishesapp.feature_recipes.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zzz.dishesapp.core.presentation.VerticalSpace

@Composable
fun ErrorState(
    modifier: Modifier = Modifier,
    message: String,
    onRetry: () -> Unit,
) {
    val orange = Color(0xFFFF5A27)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        androidx.compose.material3.Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            tint = orange,
            modifier = Modifier.size(64.dp)
        )

        VerticalSpace(16.dp)

        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )

        VerticalSpace()

        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = orange
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = "Retry",
                color = Color.White,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}