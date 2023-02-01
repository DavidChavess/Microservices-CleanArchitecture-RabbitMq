package com.davidchaves.storeapi.presentation.protocols

import com.davidchaves.storeapi.data.exception.StoreException

data class HttpResponse(
    val statusCode: Int,
    val body: Any?
) {
    companion object {
        fun created(body: Any): HttpResponse = HttpResponse(201, body)
        fun noContent(): HttpResponse = HttpResponse(204, null)
        fun error(ex: StoreException): HttpResponse =
            HttpResponse(ex.statusCode, ErrorResponse(ex.message, ex.errorDescription))
    }
}