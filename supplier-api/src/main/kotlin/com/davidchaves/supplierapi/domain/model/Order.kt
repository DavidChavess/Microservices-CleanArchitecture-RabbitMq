package com.davidchaves.supplierapi.domain.model

import java.math.BigDecimal

data class Order(
    val purchaseId: String,
    val items: List<OrderItem>
) {
    val total: BigDecimal get() = items.sumOf { it.total }
    val preparationTimeInMinutes: Int get() = items.size * 30
}