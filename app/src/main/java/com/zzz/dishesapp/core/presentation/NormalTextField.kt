package com.zzz.dishesapp.core.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Wrapper
 *
 * @author zyzz
 */
@Composable
fun NormalTextField(
    modifier: Modifier = Modifier ,
    value: String ,
    onValueChange: (String) -> Unit ,
    placeholder: String ,
    @DrawableRes leadingIcon: Int? = null ,
    leadingIconLabel: String? = null ,
    supportingText: String? = null ,
    background: Color = MaterialTheme.colorScheme.surfaceVariant ,
    onBackground: Color = MaterialTheme.colorScheme.onBackground ,
    borderColor: Color = Color.LightGray ,
    fontSize: TextUnit = 16.sp ,
    fontWeight: FontWeight = FontWeight.Normal ,
    enabled: Boolean = true ,
    singleLine: Boolean = true ,
    maxLines: Int = 10 ,
    keyboardType: KeyboardType = KeyboardType.Unspecified ,
    imeAction: ImeAction = ImeAction.Unspecified ,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.Sentences ,
    shape: Shape = OutlinedTextFieldDefaults.shape ,
    onImeAction: () -> Unit = {}
) {
    OutlinedTextField(
        value = value ,
        onValueChange = onValueChange ,
        modifier = modifier ,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = background ,
            focusedContainerColor = background ,
            unfocusedIndicatorColor = borderColor ,
            focusedIndicatorColor = borderColor
        ) ,
        shape = shape ,
        textStyle = TextStyle(
            color = onBackground ,
            fontSize = fontSize ,
            fontWeight = fontWeight
        ) ,
        enabled = enabled ,
        maxLines = maxLines ,
        singleLine = singleLine ,
        placeholder = {
            Text(
                placeholder ,
                style = TextStyle(
                    color = onBackground.copy(0.7f) ,
                    fontSize = fontSize ,
                    fontWeight = fontWeight
                )
            )
        } ,
        leadingIcon = {
            if (leadingIcon != null) {
                Icon(
                    painter = painterResource(leadingIcon) ,
                    contentDescription = leadingIconLabel ,
                    modifier = Modifier.size(25.dp) ,
                    tint = onBackground.copy(0.7f)
                )
            } else {
                null
            }
        } ,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType ,
            imeAction = imeAction,
            capitalization = capitalization
        ) ,
        keyboardActions = KeyboardActions(
            onAny = {
                onImeAction()
            }
        ) ,
        supportingText = if (supportingText != null) {
            {
                Text(supportingText)
            }
        } else {
            null
        }
    )


}