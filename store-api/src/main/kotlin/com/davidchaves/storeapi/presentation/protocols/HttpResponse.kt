package com.davidchaves.storeapi.presentation.protocols

class HttpResponse(
    val statusCode: Int,
    val body: Any
) {}