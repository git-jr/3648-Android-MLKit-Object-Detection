package com.alura.aifound.ui.camera

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.alura.aifound.data.Product
import com.alura.aifound.utils.PermissionUtils
import com.alura.aifound.utils.PermissionUtils.Companion.CAMERA_PERMISSIONS
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AnalyzeScreen(
    onNewProductDetected: (Product) -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val context = LocalContext.current
        val permissionUtils by remember { mutableStateOf(PermissionUtils(context)) }
        var permissionGranted by remember { mutableStateOf(permissionUtils.allCameraPermissionsGranted()) }

        if (permissionGranted) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                CameraScreen(onNewProductDetected = onNewProductDetected)
            }
        }

        CameraPermissionsScreen(
            permissionGranted = permissionGranted,
            onPermissionGranted = { permissionGranted = true }
        )
    }
}

@Composable
private fun CameraPermissionsScreen(
    permissionGranted: Boolean,
    onPermissionGranted: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.values.all { it }) {
            onPermissionGranted()
        }
    }

    if (!permissionGranted) {
        LaunchedEffect(Unit) {
            scope.launch {
                delay(200)
                requestPermissionLauncher.launch(CAMERA_PERMISSIONS)
            }
        }
    }
}