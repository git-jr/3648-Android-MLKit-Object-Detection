package com.alura.aifound.ui.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alura.aifound.R
import com.alura.aifound.navigation.AiFoundNavHost
import com.alura.aifound.navigation.cameraRoute
import com.alura.aifound.navigation.cartRoute
import com.alura.aifound.navigation.listRoute
import com.alura.aifound.ui.components.AppBarHome
import com.alura.aifound.ui.detail.ProductDetail
import kotlinx.coroutines.launch


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val viewModel = hiltViewModel<HomeViewModel>()
    val state by viewModel.uiState.collectAsState()

    val currentDestination by remember {
        derivedStateOf {
            navBackStackEntry?.destination
        }
    }


    val scope = rememberCoroutineScope()

    val sheetPeekHeight by animateDpAsState(
        targetValue = if (state.showPreview) 80.dp else 0.dp,
        label = "Peek height"
    )

    val scaffoldState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBarHome(
                toolbarTitle = state.toolbarTitle,
                sizeProductsInCart = state.sizeProductsInCart,
                showBackArrow = currentDestination?.route != listRoute,
                onOpenCart = {
                    navController.navigate(cartRoute)
                    viewModel.setShowPreview(false)
                },
                onBack = {
                    navController.popBackStack()
                    viewModel.setShowPreview(false)
                }
            )
        },
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                if (state.selectedProduct.name.isNotEmpty()) {
                    ProductDetail(
                        product = state.selectedProduct,
                        onAddToCart = { product, quantity ->
                            viewModel.addProductToCart(product = product, quantity = quantity)
                            scope.launch {
                                scaffoldState.bottomSheetState.partialExpand()
                            }
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    viewModel.setShowPreview(navBackStackEntry?.destination?.route == cameraRoute)
                }
            }
        },
        sheetPeekHeight = sheetPeekHeight,
        sheetShadowElevation = 1.dp,
        sheetTonalElevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.scrim.copy(alpha = 0.5f))
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            AiFoundNavHost(
                navController = navController,
                state = state,
                onSelectedProduct = { product ->
                    viewModel.setSelectProduct(product)
                },
                onAddToCart = { product, quantity ->
                    viewModel.addProductToCart(product, quantity)
                    navController.navigateUp()
                },
                onChangeToolbarTitle = { title ->
                    viewModel.setToolbarTitle(title)
                }
            )

            if (currentDestination?.route == listRoute) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(cameraRoute)
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp),
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_frame_inspect),
                        contentDescription = "Localized description",
                    )
                }
            }
        }
    }
}