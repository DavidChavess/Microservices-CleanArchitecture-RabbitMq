package com.davidchaves.supplierapi.presentation.controller

import com.davidchaves.supplierapi.data.exception.SupplierException
import com.davidchaves.supplierapi.domain.model.Order
import com.davidchaves.supplierapi.domain.model.OrderItem
import com.davidchaves.supplierapi.domain.model.Product
import com.davidchaves.supplierapi.domain.usecases.CreateOrder
import com.davidchaves.supplierapi.domain.usecases.PurchaseItemModel
import com.davidchaves.supplierapi.domain.usecases.PurchaseModel
import com.davidchaves.supplierapi.presentation.protocols.ErrorResponse
import com.davidchaves.supplierapi.presentation.protocols.HttpRequest
import com.davidchaves.supplierapi.presentation.protocols.HttpResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal

@ExtendWith(value = [MockitoExtension::class])
class CreateOrderControllerTest {

    @Mock
    private lateinit var createOrder: CreateOrder

    @InjectMocks
    private lateinit var createOrderController: CreateOrderController

    @Test
    @DisplayName("Deve criar pedido")
    fun shouldCreateOrder() {
        val purchaseModel = PurchaseModel("purchase id", listOf(PurchaseItemModel(productUuid = "uuid1", quantity = BigDecimal(2))))
        val product = Product(
            id = 1,
            uuid = "uuid1",
            name = "any product",
            description = "any description",
            price = BigDecimal(4)
        )
        given(createOrder.createOrder(purchaseModel))
            .willReturn(Order(purchaseId= "purchase id", items = listOf(OrderItem(product, BigDecimal(2)))))

        val httpResponse: HttpResponse = createOrderController.handle(HttpRequest(body = purchaseModel))
        val orderResponse = httpResponse.body as Order

        assertEquals(201, httpResponse.statusCode)
        assertEquals(orderResponse.total, BigDecimal(8))
        verify(createOrder).createOrder(purchaseModel)
    }

    @Test
    @DisplayName("Deve repassar erro se createOrder lançar")
    fun shouldThrowErrorIfCreateOrderThrows() {
        val purchaseModel = PurchaseModel(
            "purchase id",
            listOf(PurchaseItemModel("any product uuid", BigDecimal(2)))
        )
        given(createOrder.createOrder(purchaseModel)).willThrow(SupplierException("any error", 400))

        val httpResponse: HttpResponse = createOrderController.handle(HttpRequest(body = purchaseModel))

        assertEquals(400, httpResponse.statusCode)
        assertEquals(ErrorResponse("any error"), httpResponse.body)
        verify(createOrder).createOrder(purchaseModel)
    }
}