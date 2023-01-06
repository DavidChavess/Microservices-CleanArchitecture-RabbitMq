package com.davidchaves.storeapi.data.exception

open class StoreException(
    val statusCode: Int,
    val errorDescription: String,
    override val message: String
): RuntimeException(errorDescription) {}
