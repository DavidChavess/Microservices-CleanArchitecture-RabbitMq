package com.davidchaves.storeapi.presentation.controllers

import com.davidchaves.storeapi.data.exception.StoreException
import com.davidchaves.storeapi.domain.models.SavePurchaseModel
import com.davidchaves.storeapi.domain.usecases.SavePurchase
import com.davidchaves.storeapi.main.annotations.Component
import com.davidchaves.storeapi.presentation.protocols.Controller
import com.davidchaves.storeapi.presentation.protocols.ErrorResponse
import com.davidchaves.storeapi.presentation.protocols.HttpRequest
import com.davidchaves.storeapi.presentation.protocols.HttpResponse

@Component
class PurchaseController(
    private val savePurchase: SavePurchase
) : Controller {

    override fun handle(httpRequest: HttpRequest): HttpResponse {
        return try {
            val purchase = httpRequest.body as SavePurchaseModel
            HttpResponse.created(savePurchase.save(purchase))
        } catch (ex: StoreException) {
            HttpResponse(ex.statusCode, ErrorResponse(ex.message, ex.errorDescription))
        }
    }
}