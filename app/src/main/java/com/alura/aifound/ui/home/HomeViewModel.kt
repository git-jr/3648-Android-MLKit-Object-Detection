package com.alura.aifound.ui.home

import androidx.lifecycle.ViewModel
import com.alura.aifound.data.Product
import com.alura.aifound.sampleData.ProductSample
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState>
        get() = _uiState.asStateFlow()

    fun setShowPreview(show: Boolean) {
        _uiState.value = _uiState.value.copy(
            showPreview = show
        )
    }

    fun addProductToCart(product: Product, quantity: Int) {
        val productsInCart = _uiState.value.productsInCart.toMutableList()
        val index = productsInCart.indexOfFirst { it.id == product.id }
        if (index != -1) {
            productsInCart[index] =
                productsInCart[index].copy(quantity = productsInCart[index].quantity + quantity)
        } else {
            productsInCart.add(product.copy(quantity = quantity))
        }

        _uiState.value = _uiState.value.copy(
            productsInCart = productsInCart,
            sizeProductsInCart = productsInCart.sumOf { it.quantity },
            totalPurchase = productsInCart.sumOf { it.price * it.quantity }
        )
    }


    fun setSelectProduct(product: Product) {
        _uiState.value = _uiState.value.copy(
            selectedProduct = product,
            showPreview = product.name.isNotEmpty()
        )
    }

    fun setToolbarTitle(text: String) {
        _uiState.value = _uiState.value.copy(
            toolbarTitle = text
        )
    }
}