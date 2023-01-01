package com.yilmazgokhan.composefirebase.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yilmazgokhan.composefirebase.R

@Composable
fun DefaultToolbar(
    title: String? = "",
    textColor: Color = Color.White,
    tint: Color = Color.White,
    backgroundColor: Color = Color.Blue,
    contentDescription: String = "",
    onBackPressClick: () -> Unit = { },
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
        ) {
            title?.let {
                TextDefault(
                    modifier = Modifier.align(Alignment.Center),
                    text = it,
                    color = textColor
                )
            }
        }

        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = onBackPressClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_baseline_arrow_back_ios_24),
                contentDescription = contentDescription,
                tint = tint
            )
        }
    }
}

@Composable
fun ToolbarWithEndIcon(
    title: String? = "",
    textColor: Color = Color.White,
    tint: Color = Color.White,
    backgroundColor: Color = Color.Blue,
    endIconRes: Int,
    contentDescription: String = "",
    endIconClick: () -> Unit = { },
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor),
    ) {
        title?.let {
            TextDefault(
                modifier = Modifier.align(Alignment.Center),
                text = it,
                color = textColor
            )
        }

        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = endIconClick
        ) {
            Icon(
                painter = painterResource(endIconRes),
                contentDescription = contentDescription,
                tint = tint
            )
        }
    }
}

@Composable
fun ToolbarWithEndIcon(
    title: String? = "",
    textColor: Color = Color.White,
    tint: Color = Color.White,
    backgroundColor: Color = Color.Blue,
    endIconRes: Int,
    contentDescription: String = "",
    endIconClick: () -> Unit = { },
    onBackPressClick: () -> Unit = { },
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
                //.padding(vertical = 16.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackPressClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_arrow_back_ios_24),
                    contentDescription = contentDescription,
                    tint = tint
                )
            }

            title?.let {
                TextDefault(
                    text = it,
                    color = textColor
                )
            }

            IconButton(
                onClick = endIconClick
            ) {
                Icon(
                    painter = painterResource(endIconRes),
                    contentDescription = contentDescription,
                    tint = tint
                )
            }
        }
    }
}