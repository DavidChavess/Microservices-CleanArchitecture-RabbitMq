package com.davidchaves.supplierapi.presentation.controller

import com.davidchaves.supplierapi.data.exception.SupplierException
import com.davidchaves.supplierapi.domain.usecases.GetSupplierByState
import com.davidchaves.supplierapi.domain.usecases.model.GetSupplierByStateModel
import com.davidchaves.supplierapi.main.annotations.ControllerComponent
import com.davidchaves.supplierapi.presentation.protocols.Controller
import com.davidchaves.supplierapi.presentation.protocols.ErrorResponse
import com.davidchaves.supplierapi.presentation.protocols.HttpRequest
import com.davidchaves.supplierapi.presentation.protocols.HttpResponse

@ControllerComponent
class GetSupplierByStateController(private val getSupplierByState: GetSupplierByState) : Controller {
    override fun handle(httpRequest: HttpRequest): HttpResponse = try {
        HttpResponse(200, getSupplierByState.getSupplierByState((httpRequest.body as GetSupplierByStateModel).state))
    } catch (ex: SupplierException) {
        HttpResponse(ex.statusCode, ErrorResponse(ex.message))
    }
}