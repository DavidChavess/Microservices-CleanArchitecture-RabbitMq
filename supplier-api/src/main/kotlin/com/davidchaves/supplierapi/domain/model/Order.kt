package com.davidchaves.supplierapi.domain.model

import java.math.BigDecimal

data class Order(
    val id: Int? = null,
    val items: List<OrderItem>
) {
    val total: BigDecimal get() = items.sumOf { it.total }
}