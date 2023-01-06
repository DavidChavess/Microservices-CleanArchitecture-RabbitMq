package com.davidchaves.supplierapi.presentation.protocols

interface Controller {
    fun handle (httpRequest: HttpRequest): HttpResponse
}