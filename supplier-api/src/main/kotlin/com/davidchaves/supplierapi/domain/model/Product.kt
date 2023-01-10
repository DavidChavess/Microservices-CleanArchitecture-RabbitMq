package com.davidchaves.supplierapi.domain.model

import java.math.BigDecimal

data class Product(
    val id: Int,
    val uuid: String,
    val name: String,
    val description: String?,
    val price: BigDecimal
)