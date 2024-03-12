package com.alura.aifound.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alura.aifound.R

@Composable
fun AppBarHome(
    toolbarTitle: String? = null,
    showBackArrow: Boolean = false,
    sizeProductsInCart: Int,
    onOpenCart: () -> Unit,
    onBack: () -> Unit
) {
    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .padding(start = 8.dp, end = 16.dp)
            .height(56.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            if (showBackArrow) {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = toolbarTitle ?: stringResource(id = R.string.app_name),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 18.sp
            )
        }

        BadgedBox(
            modifier = Modifier.padding(horizontal = 16.dp),
            badge = {
                Badge(
                    modifier = Modifier.offset(y = (-8).dp),
                    content = {
                        Text(sizeProductsInCart.toString())
                    },
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            }) {
            Icon(
                Icons.Default.ShoppingCart,
                contentDescription = "Shopping Cart",
                modifier = Modifier
                    .offset(x = 8.dp),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onOpenCart() }
                .padding(end = 16.dp, top = 8.dp, bottom = 8.dp),
        ) {
            Spacer(modifier = Modifier.size(24.dp))
        }
    }

    HorizontalDivider(thickness = 1.dp, color = Color.LightGray, modifier = Modifier.fillMaxWidth())
}