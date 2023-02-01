package com.davidchaves.storeapi.presentation.controllers

import com.davidchaves.storeapi.data.exception.StoreException
import com.davidchaves.storeapi.domain.models.SavePaymentModel
import com.davidchaves.storeapi.domain.usecases.SavePayment
import com.davidchaves.storeapi.main.annotations.Component
import com.davidchaves.storeapi.presentation.protocols.Controller
import com.davidchaves.storeapi.presentation.protocols.HttpRequest
import com.davidchaves.storeapi.presentation.protocols.HttpResponse

@Component
class PaymentController(private val savePayment: SavePayment) : Controller {
    override fun handle(httpRequest: HttpRequest): HttpResponse = try {
        savePayment.save(httpRequest.body as SavePaymentModel)
        HttpResponse.noContent()
    } catch (ex: StoreException) {
        HttpResponse.error(ex)
    }
}