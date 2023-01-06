package com.davidchaves.supplierapi.domain.model

data class Supplier(
    val name: String,
    val address: String,
    val zipcode: String,
    val state: String
) {}
