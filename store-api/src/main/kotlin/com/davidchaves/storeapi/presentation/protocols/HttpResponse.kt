package com.davidchaves.storeapi.presentation.protocols

class HttpResponse(
    val statusCode: Int,
    val body: Any
) {
    companion object {
        fun created(body: Any): HttpResponse = HttpResponse(201, body)
    }
}