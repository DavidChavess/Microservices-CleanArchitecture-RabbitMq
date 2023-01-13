package com.davidchaves.supplierapi.presentation.controller

import com.davidchaves.supplierapi.data.exception.SupplierNotFound
import com.davidchaves.supplierapi.domain.model.Supplier
import com.davidchaves.supplierapi.domain.usecases.GetSupplierByState
import com.davidchaves.supplierapi.domain.usecases.model.GetSupplierByStateModel
import com.davidchaves.supplierapi.presentation.protocols.HttpRequest
import com.davidchaves.supplierapi.presentation.protocols.HttpResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [MockitoExtension::class])
class GetSupplierByStateControllerTest {

    @Mock
    private lateinit var getSupplierByState: GetSupplierByState

    @InjectMocks
    private lateinit var getSupplierByStateController: GetSupplierByStateController

    @Test
    @DisplayName("Deve buscar fornecedor por estado")
    fun shouldFindSupplierByState() {
        given(getSupplierByState.getSupplierByState("SP"))
            .willReturn(Supplier("Supplier 1", "Rua teste", "145", "SP"))

        val httpRequest = HttpRequest(body = GetSupplierByStateModel(state = "SP"))
        val httpResponse: HttpResponse = getSupplierByStateController.handle(httpRequest)

        assertEquals(200, httpResponse.statusCode)
        assertEquals(Supplier("Supplier 1", "Rua teste", "145", "SP"), httpResponse.body)
    }

    @Test
    @DisplayName("Deve retornar erro se a busca por fornecedor lançar erro")
    fun shouldReturnErrorIfFindSupplierByStateThrows() {
        given(getSupplierByState.getSupplierByState("SP"))
            .willThrow(SupplierNotFound())

        val httpRequest = HttpRequest(body = GetSupplierByStateModel(state = "SP"))
        val httpResponse: HttpResponse = getSupplierByStateController.handle(httpRequest)

        assertEquals(404, httpResponse.statusCode)
    }
}