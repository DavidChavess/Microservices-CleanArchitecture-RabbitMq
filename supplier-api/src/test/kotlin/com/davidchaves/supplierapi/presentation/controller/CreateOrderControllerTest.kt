package com.davidchaves.supplierapi.presentation.controller

import com.davidchaves.supplierapi.data.exception.SupplierException
import com.davidchaves.supplierapi.domain.model.Order
import com.davidchaves.supplierapi.domain.model.OrderItem
import com.davidchaves.supplierapi.domain.model.Product
import com.davidchaves.supplierapi.domain.usecases.CreateOrder
import com.davidchaves.supplierapi.domain.usecases.model.OrderItemModel
import com.davidchaves.supplierapi.domain.usecases.model.OrderModel
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
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal

@ExtendWith(value = [SpringExtension::class])
class CreateOrderControllerTest {

    @Mock
    private lateinit var createOrder: CreateOrder

    @InjectMocks
    private lateinit var createOrderController: CreateOrderController

    @Test
    @DisplayName("Deve criar pedido")
    fun shouldCreateOrder() {
        val orderModel = OrderModel(listOf(OrderItemModel(productUuid = "uuid1", quantity = BigDecimal(2))))

        given(createOrder.createOrder(orderModel)).willReturn(getOrder())

        val httpResponse: HttpResponse = createOrderController.handle(HttpRequest(body = orderModel))
        val orderResponse = httpResponse.body as Order

        assertEquals(201, httpResponse.statusCode)
        assertEquals(orderResponse.total, BigDecimal(8))
        verify(createOrder).createOrder(orderModel)
    }

    @Test
    @DisplayName("Deve repassar erro se createOrder lançar")
    fun shouldThrowErrorIfCreateOrderThrows() {
        val orderModel = OrderModel(listOf(OrderItemModel("any product uuid", BigDecimal(2))))
        given(createOrder.createOrder(orderModel)).willThrow(SupplierException("any error", 400))

        val httpResponse: HttpResponse = createOrderController.handle(HttpRequest(body = orderModel))

        assertEquals(400, httpResponse.statusCode)
        assertEquals(ErrorResponse("any error"), httpResponse.body)
        verify(createOrder).createOrder(orderModel)
    }

    private fun getOrder(): Order {
        val product =
            Product(
                id = 1,
                uuid = "uuid1",
                name = "any product",
                description = "any description",
                price = BigDecimal(4)
            )
        return Order(null, listOf(OrderItem(product = product, quantity = BigDecimal(2))))
    }
}