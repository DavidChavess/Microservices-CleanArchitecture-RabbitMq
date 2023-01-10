package com.davidchaves.supplierapi.domain.model

import java.math.BigDecimal

data class OrderItem(
    val product: Product,
    val quantity: BigDecimal
) {
    val total: BigDecimal get() = quantity.multiply(product.price)
}