package com.alura.aifound.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.alura.aifound.data.Product


@Composable
fun ProductDetail(
    product: Product,
    onAddToCart: (Product, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var quantity by remember { mutableIntStateOf(1) }
    val roundedPrice = String.format("%.2f", product.price * quantity)

    Column(
        modifier = modifier
            .background(Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(50.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = product.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = "R$ ${product.price}",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                product.imageUrl,
                contentDescription = "imagem do produto ${product.name}",
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.size(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total", color = MaterialTheme.colorScheme.secondaryContainer,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.onSecondaryContainer)
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                    )

                    Text(
                        text = roundedPrice,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "-", color = MaterialTheme.colorScheme.errorContainer,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                            .clickable {
                                if (quantity > 1) {
                                    quantity--
                                }
                            }
                            .background(
                                MaterialTheme.colorScheme.secondary,
                                RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
                            )
                            .padding(vertical = 8.dp, horizontal = 16.dp)

                    )
                    Text(
                        text = quantity.toString(),
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "+", color = MaterialTheme.colorScheme.errorContainer,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clip(RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
                            .clickable {
                                quantity++
                            }
                            .background(
                                MaterialTheme.colorScheme.secondary,
                                RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                            )
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.size(20.dp))

            Text(
                text = "Descrição",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = LoremIpsum(20).values.first(),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.outline
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.size(32.dp))

            Button(
                onClick = {
                    onAddToCart(product, quantity)
                    quantity = 1
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    text = "Adicionar ao carrinho",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.size(16.dp))
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Adicionar ao carrinho",
                    tint = MaterialTheme.colorScheme.secondaryContainer
                )
            }

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}