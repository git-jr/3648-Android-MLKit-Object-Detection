package com.alura.aifound.ui.home

import com.alura.aifound.data.Product

data class HomeScreenUiState(
    val showPreview: Boolean = false,
    val productsInCart: List<Product> = emptyList(),
    val sizeProductsInCart: Int = 0,
    val totalPurchase: Double = 0.0,
    val selectedProduct: Product = Product(),
    val toolbarTitle: String? = null,
)