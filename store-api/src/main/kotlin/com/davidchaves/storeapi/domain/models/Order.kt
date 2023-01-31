package com.davidchaves.storeapi.domain.models

import java.math.BigDecimal

data class Product(
    val id: Int,
    val uuid: String,
    val name: String,
    val description: String?,
    val price: BigDecimal
)

data class OrderItem(
    val product: Product,
    val quantity: BigDecimal,
    val total: BigDecimal
)

data class Order(
    val id: Int,
    val items: List<OrderItem>,
    val total: BigDecimal,
    val preparationTimeInMinutes: Int
)