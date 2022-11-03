package com.yilmazgokhan.composefirebase.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import com.yilmazgokhan.composefirebase.R

@Composable
fun Loading(visible: Boolean) {
    if (!visible) {
        return
    }
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Loader()
    }
}

@Composable
private fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
    val progress by animateLottieCompositionAsState(composition,
        iterations = LottieConstants.IterateForever)
    LottieAnimation(
        composition = composition,
        progress = { progress }
    )
}