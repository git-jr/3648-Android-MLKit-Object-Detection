package com.alura.aifound.sampleData

import com.alura.aifound.R
import com.alura.aifound.data.Product
import com.alura.aifound.extensions.takeTwoWords

class ProductSample {
    companion object {
        fun getProducts(): List<Product> {
            return products
        }

        fun findProductByName(name: String): Product {
            val firstTwoNames = name.takeTwoWords()
            return products.find { it.description.contains(firstTwoNames) } ?: Product()
        }
    }
}

val products = listOf(
    Product(
        name = "Refrigerante de Coca",
        price = 5.99,
        description = "Coca-Cola - 6 pack, 12 fl oz cans, Coca-Cola Soda - 16 fl oz can, Original Formula - 24 pack, 12 fl oz cans, Coca Cola, CocaCola Soda, Cherry Coke",
        imageUrl = R.drawable.produto_1
    ),
    Product(
        name = "Batata Chips",
        price = 8.43,
        description = "Lays Potato Chips, Limon Flavored - 2.75 oz, Lays Potato Chips, Dill Pickle Flavored - 2.75 oz, Lay's Classic Potato Chips 8 Oz Bag, Fritos The Original Corn Chips, Lays Classic Potato Chips",
        imageUrl = R.drawable.produto_4
    ),
    Product(
        name = "Catchup",
        price = 11.48,
        description = "Heinz Ketchup Tomato, Heinz Tomato Ketchup - 20 oz, Heinz Tomato Ketchup - 32 oz, Classico Riserva Pasta Sauce",
        imageUrl = R.drawable.produto_3
    ),
    Product(
        name = "Energético Monster",
        price = 7.45,
        description = "Monster Juice Energy Juice, Mango Loco - 16 fl oz, Juice Monster Mango Loco, Energy Drink, 16 Ounce (Pack of 24), Monster Energy Drink, Ultra Blue - 16 fl oz",
        imageUrl = R.drawable.produto_6
    ),
    Product(
        name = "Macarrão Instantâneo",
        price = 5.49,
        description = "Cup Noodle Noodle Soup, Ramen, Curry - 28 oz, Osem Bamba - 2.1 oz, Bobs Red Mill Pancake & Waffle Mix, Grain Free, Paleo - 13 oz, Nissin Cup Noodles Chicken Flavor - 2.25oz",
        imageUrl = R.drawable.produto_5
    ),
    Product(
        name = "Biscoito Oreo",
        price = 14.44,
        description = "Nabisco Oreo Sandwich Cookies - Each, Cool Whip Oreo Whipped Topping, Mix-Ins - 8 oz, 9 Lives Cat Food, with Real Ocean Whitefish, Meaty Pate - 24 pack, 5.5 oz cans",
        imageUrl = R.drawable.produto_2
    ),
    Product(
        name = "Nutella de Pimenta",
        price = 56.56,
        description = "Nutella Hazelnut Spread, with Cocoa - 7.7 oz, Ferrero Nutella Chocolate Hazelnut Spread with Cocoa - 26.5 oz jar, Nutella Chocolate Hazelnut Spread - 13 oz jar",
        imageUrl = R.drawable.produto_7
    ),
)