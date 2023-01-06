package com.davidchaves.supplierapi.data.exception

open class SupplierException(
    override val message: String,
    val statusCode: Int
): RuntimeException(message)