package com.davidchaves.supplierapi.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal

data class Product(
    @JsonIgnore val id: Int,
    val uuid: String,
    val name: String,
    val description: String?,
    val price: BigDecimal
)