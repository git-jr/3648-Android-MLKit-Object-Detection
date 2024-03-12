package com.alura.aifound.ui.camera

import com.alura.aifound.data.ProductObject

data class CameraScreenUiState(
    val imageWidth: Int = 0,
    val imageHeight: Int = 0,
    val screenWidth: Int = 0,
    val screenHeight: Int = 0,
    val textMessage: String? = null,
    val detectedProduct: ProductObject? = null
)