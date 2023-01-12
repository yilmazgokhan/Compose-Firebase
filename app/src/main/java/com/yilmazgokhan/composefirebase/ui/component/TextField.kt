package com.yilmazgokhan.composefirebase.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    value: String,
    iconVector: ImageVector? = null,
    iconText: String? = null,
    enabled: Boolean = true,
    label: String? = null,
    placeholder: String? = null,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        leadingIcon = {
            iconVector?.let {
                Icon(
                    imageVector = it,
                    contentDescription = iconText
                )
            }
        },
        onValueChange =  onValueChange,
        label = {
            label?.let {
                Text(text = it)
            }
        },
        placeholder = {
            placeholder?.let {
                Text(text = placeholder)
            }
        },
        enabled = enabled
    )
}