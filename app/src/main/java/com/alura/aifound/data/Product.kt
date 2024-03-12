package com.alura.aifound.data

import androidx.annotation.DrawableRes
import java.util.UUID


data class Product(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val quantity: Int = 0,
    val description: String = "",
    @DrawableRes val imageUrl: Int = 0,
    val price: Double = 0.0
)