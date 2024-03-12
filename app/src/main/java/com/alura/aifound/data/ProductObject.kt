package com.alura.aifound.data

import androidx.compose.ui.geometry.Rect

data class ProductObject(
    val product: Product,
    val boundingBox: Rect = Rect(0f, 0f, 0f, 0f),
    val coordinateX: Float = 0f,
    val coordinateY: Float = 0f
)

