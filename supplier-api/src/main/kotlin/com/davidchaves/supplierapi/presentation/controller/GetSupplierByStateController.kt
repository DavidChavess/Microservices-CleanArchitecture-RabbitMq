package com.davidchaves.supplierapi.presentation.controller

import com.davidchaves.supplierapi.data.exception.SupplierException
import com.davidchaves.supplierapi.domain.model.GetSupplierFilter
import com.davidchaves.supplierapi.domain.usecases.GetSupplierByState
import com.davidchaves.supplierapi.presentation.protocols.Controller
import com.davidchaves.supplierapi.presentation.protocols.ErrorResponse
import com.davidchaves.supplierapi.presentation.protocols.HttpRequest
import com.davidchaves.supplierapi.presentation.protocols.HttpResponse
import com.davidchaves.supplierapi.main.annotations.Controller as ControllerComponent

@ControllerComponent
class GetSupplierByStateController(private val getSupplierByState: GetSupplierByState) : Controller {
    override fun handle(httpRequest: HttpRequest): HttpResponse {
        return try {
            val filter = httpRequest.body as GetSupplierFilter
            HttpResponse(200, getSupplierByState.getSupplierByState(filter.state))
        } catch (ex: SupplierException) {
            HttpResponse(ex.statusCode, ErrorResponse(ex.message))
        }
    }
}