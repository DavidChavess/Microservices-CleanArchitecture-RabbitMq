package com.davidchaves.storeapi.presentation.protocols

interface Controller {
    fun handle(httpRequest: HttpRequest): HttpResponse
}