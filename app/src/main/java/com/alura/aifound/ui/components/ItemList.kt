package com.alura.aifound.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alura.aifound.data.Product

@Composable
fun ItemsList(
    items: List<Product>,
    modifier: Modifier = Modifier,
    onItemClicked: (Product) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(items) { product ->
                ListItem(
                    modifier = Modifier
                        .clickable { onItemClicked(product) }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .clip(shape = RoundedCornerShape(15.dp)),
                    headlineContent = {
                        Text(
                            product.name,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    supportingContent = {
                        Text(
                            "Descrição: ${product.description}",
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3
                        )
                    },
                    leadingContent = {
                        AsyncImage(
                            product.imageUrl,
                            contentDescription = "Product image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(
                                    shape = RoundedCornerShape(15.dp)
                                )
                        )
                    },
                    trailingContent = {
                        Text(
                            text = if (product.quantity == 0) "R$ ${product.price}" else "${product.quantity} x R$ ${product.price}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )

                    },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        supportingColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        headlineColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        trailingIconColor = MaterialTheme.colorScheme.onBackground,
                    )

                )
            }
        }
    }
}