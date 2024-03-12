package com.alura.aifound.ui.camera

import androidx.lifecycle.ViewModel
import com.alura.aifound.data.Product
import com.alura.aifound.sampleData.ProductSample
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class CameraViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CameraScreenUiState())
    val uiState: StateFlow<CameraScreenUiState>
        get() = _uiState.asStateFlow()


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