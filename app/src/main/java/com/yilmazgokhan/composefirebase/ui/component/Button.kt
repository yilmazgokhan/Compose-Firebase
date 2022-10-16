package com.yilmazgokhan.composefirebase.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yilmazgokhan.composefirebase.ui.theme.Purple200

@Composable
fun ButtonDefault(
    text: String,
    click: () -> Unit = {}
) {
    Button(
        onClick = click,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        TextSecondary(
            text = text,
            color = Color.White
        )
    }
}

@Composable
fun ButtonWithBorder(
    text: String,
    textColor: Color = Purple200,
    borderColor: Color = Purple200,
    backgroundColor: Color = Color.White,
    click: () -> Unit = {}
) {
    Button(
        onClick = click,
        border = BorderStroke(1.dp, borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = borderColor,
            backgroundColor = backgroundColor
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextSecondary(
            text = text,
            color = textColor
        )
    }
}

@Composable
fun ButtonWithRoundCornerShape(
    text: String,
    click: () -> Unit = {}
) {
    Button(
        onClick = click,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        TextSecondary(
            text = text
        )
    }
}

@Composable
fun ButtonWithIcon(
    text: String,
    iconRes: Int,
    contentDescription: String,
    click: () -> Unit = {}
) {
    Button(
        onClick = click,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        Image(
            painterResource(id = iconRes),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp)
        )

        TextSecondary(
            text = text,
            modifier = Modifier.padding(6.dp)
        )
    }
}

@Composable
fun ButtonWithElevation(
    text: String,
    click: () -> Unit = {}
) {
    Button(
        onClick = click,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        ),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        TextSecondary(
            text = text
        )
    }
}