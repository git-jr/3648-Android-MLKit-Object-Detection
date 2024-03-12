package com.alura.aifound.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Float.pxToDp(): Dp {
    val density = LocalDensity.current.density
    return (this / density).dp
}

@Composable
fun Dp.dpToPx(): Float {
    val density = LocalDensity.current.density
    return this.value * density
}

