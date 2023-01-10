package com.davidchaves.supplierapi.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal

data class Order(
    @JsonIgnore val id: Int? = null,
    val items: List<OrderItem>
) {
    val total: BigDecimal get() = items.sumOf { it.total }
}