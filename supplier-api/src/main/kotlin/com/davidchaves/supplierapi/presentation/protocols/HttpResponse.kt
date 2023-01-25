package com.davidchaves.supplierapi.presentation.protocols

class HttpResponse(val statusCode: Int, val body: Any){
    companion object {
        fun created(body: Any) = HttpResponse(201, body)
        fun ok(body: Any): HttpResponse = HttpResponse(200, body)
    }
}
