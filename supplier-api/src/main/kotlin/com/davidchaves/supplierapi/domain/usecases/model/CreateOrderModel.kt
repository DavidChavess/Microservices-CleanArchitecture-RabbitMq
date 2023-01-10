package com.davidchaves.supplierapi.domain.usecases.model

import java.math.BigDecimal

class OrderItemModel(
    val productUuid: String,
    val quantity: BigDecimal
)

class OrderModel(
    val items: List<OrderItemModel>
)