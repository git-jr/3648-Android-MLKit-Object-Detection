package com.alura.aifound.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alura.aifound.data.Product
import com.alura.aifound.sampleData.ProductSample
import com.alura.aifound.ui.camera.AnalyzeScreen
import com.alura.aifound.ui.cart.CartScreen
import com.alura.aifound.ui.components.ItemsList
import com.alura.aifound.ui.detail.ProductDetail
import com.alura.aifound.ui.home.HomeScreenUiState


internal const val listRoute = "list"
internal const val cameraRoute = "camera"
internal const val cartRoute = "cart"
internal const val productDetailsRoute = "productDetails"


@Composable
fun AiFoundNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    state: HomeScreenUiState,
    onChangeToolbarTitle: (String) -> Unit = {},
    onSelectedProduct: (Product) -> Unit = {},
    onAddToCart: (Product, Int) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = listRoute,
        modifier = modifier,
    ) {
        composable(listRoute) {
            onChangeToolbarTitle("AI Found")
            ItemsList(
                items = ProductSample.getProducts(),
                onItemClicked = { product ->
                    onSelectedProduct(product)
                    navController.navigate(productDetailsRoute)
                },
            )
        }

        composable(cameraRoute) {
            onChangeToolbarTitle("Escaneando")
            AnalyzeScreen(
                onNewProductDetected = { product ->
                    onSelectedProduct(product)
                }
            )
        }

        composable(cartRoute) {
            onChangeToolbarTitle("Carrinho")
            CartScreen(
                productsInCart = state.productsInCart,
                totalPurchase = state.totalPurchase,
            )
        }

        composable(productDetailsRoute) {
            onChangeToolbarTitle(state.selectedProduct.name)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                ProductDetail(
                    product = state.selectedProduct,
                    onAddToCart = { product, quantity ->
                        onAddToCart(product, quantity)
                    }
                )
            }
        }
    }
}


