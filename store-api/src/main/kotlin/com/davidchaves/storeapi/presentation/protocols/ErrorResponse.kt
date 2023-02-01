package com.davidchaves.storeapi.presentation.protocols

data class ErrorResponse(
    val error: String,
    val errorDescription: String
)