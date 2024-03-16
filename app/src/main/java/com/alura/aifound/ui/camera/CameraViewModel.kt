package com.alura.aifound.ui.camera

import androidx.compose.ui.graphics.toComposeRect
import androidx.lifecycle.ViewModel
import com.alura.aifound.data.Product
import com.alura.aifound.data.ProductObject
import com.alura.aifound.sampleData.ProductSample
import com.google.mlkit.vision.objects.DetectedObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class CameraViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CameraScreenUiState())
    val uiState: StateFlow<CameraScreenUiState>
        get() = _uiState.asStateFlow()

    fun setObjectDetected(objectDetected: DetectedObject) {
        with(_uiState.value) {
            val product = getProductByName(objectDetected.labels.firstOrNull()?.text.toString())
            if (product.name.isEmpty()) {
                resetDetectedProduct()
            }

            val boundingBox = objectDetected.boundingBox.toComposeRect()
            val coordinateX = (boundingBox.topLeft.x / imageWidth * screenWidth)
            val coordinateY = (boundingBox.topLeft.y / imageHeight * screenHeight)


            _uiState.value = _uiState.value.copy(
                detectedProduct = ProductObject(
                    product = product,
                    boundingBox = boundingBox,
                    coordinateX = coordinateX,
                    coordinateY = coordinateY
                )
            )
        }
    }

    fun setTextMessage(text: String) {
        _uiState.value = _uiState.value.copy(
            textMessage = text
        )
    }

    fun setImageSize(width: Int, height: Int) {
        _uiState.value = _uiState.value.copy(
            imageWidth = width,
            imageHeight = height
        )
    }

    fun setScreenSize(screenWidth: Int, screenHeight: Int) {
        _uiState.value = _uiState.value.copy(
            screenWidth = screenWidth,
            screenHeight = screenHeight
        )
    }

    fun getProductByName(name: String): Product {
        return ProductSample.findProductByName(name)
    }

    fun resetDetectedProduct() {
        _uiState.value = _uiState.value.copy(
            detectedProduct = null
        )
    }
}