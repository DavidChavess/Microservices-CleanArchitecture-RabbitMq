package com.davidchaves.supplierapi.presentation.controller

import com.davidchaves.supplierapi.data.exception.SupplierException
import com.davidchaves.supplierapi.domain.usecases.CreateOrder
import com.davidchaves.supplierapi.domain.usecases.PurchaseModel
import com.davidchaves.supplierapi.main.annotations.ControllerComponent
import com.davidchaves.supplierapi.presentation.protocols.Controller
import com.davidchaves.supplierapi.presentation.protocols.ErrorResponse
import com.davidchaves.supplierapi.presentation.protocols.HttpRequest
import com.davidchaves.supplierapi.presentation.protocols.HttpResponse

@ControllerComponent
class CreateOrderController(private val createOrder: CreateOrder) : Controller {
    override fun handle(httpRequest: HttpRequest): HttpResponse = try {
        HttpResponse.created(createOrder.createOrder(httpRequest.body as PurchaseModel))
    } catch (ex: SupplierException) {
        HttpResponse(ex.statusCode, ErrorResponse(ex.message))
    }
}