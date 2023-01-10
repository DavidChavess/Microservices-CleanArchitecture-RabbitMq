package com.davidchaves.supplierapi.data.usecases

import com.davidchaves.supplierapi.data.exception.ProductNotFoundException
import com.davidchaves.supplierapi.data.protocols.OrderRepository
import com.davidchaves.supplierapi.data.protocols.ProductRepository
import com.davidchaves.supplierapi.domain.model.Order
import com.davidchaves.supplierapi.domain.model.OrderItem
import com.davidchaves.supplierapi.domain.model.Product
import com.davidchaves.supplierapi.domain.usecases.model.OrderItemModel
import com.davidchaves.supplierapi.domain.usecases.model.OrderModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal

@ExtendWith(value = [SpringExtension::class])
class DbCreateOrderTest {

    @Mock
    private lateinit var productRepository: ProductRepository

    @Mock
    private lateinit var orderRepository: OrderRepository

    @InjectMocks
    private lateinit var dbCreateOrder: DbCreateOrder

    @Test
    @DisplayName("Deve chamar busca de produtos com valores corretos")
    fun shouldFindProductsWithCorrectValues() {
        given(productRepository.findByUuidIn(listOf("uuid1", "uuid2")))
            .willReturn(
                listOf(
                    Product(1, "uuid1", "product1", "", BigDecimal(1)),
                    Product(2, "uuid2", "product2", "", BigDecimal(1))
                )
            )

        val item1 = OrderItemModel("uuid1", BigDecimal(2))
        val item2 = OrderItemModel("uuid2", BigDecimal(2))

        dbCreateOrder.createOrder(OrderModel(listOf(item1, item2)))
        verify(productRepository).findByUuidIn(listOf("uuid1", "uuid2"))
    }

    @Test
    @DisplayName("Deve lnçar erro de produto não encontrado")
    fun shouldThrowProductNotFound() {
        given(productRepository.findByUuidIn(listOf("uuid1", "uuid2")))
            .willReturn(
                listOf(
                    Product(1, "uuid1", "product1", "", BigDecimal(1))
                )
            )

        val item1 = OrderItemModel("uuid1", BigDecimal(2))
        val item2 = OrderItemModel("uuid2", BigDecimal(2))

        assertThrows<ProductNotFoundException> { dbCreateOrder.createOrder(OrderModel(listOf(item1, item2))) }
        verify(productRepository).findByUuidIn(listOf("uuid1", "uuid2"))
    }

    @Test
    @DisplayName("Deve chamar salvamento de pedido com valores corretos e calcular o total dos itens")
    fun shouldCreateOrderWithCorrectValues() {
        val products = listOf(
            Product(1, "uuid1", "product1", "", BigDecimal(2)),
        )
        given(productRepository.findByUuidIn(listOf("uuid1"))).willReturn(products)

        val item = OrderItemModel("uuid1", BigDecimal(2))
        val orderModel = OrderModel(listOf(item))
        val order = dbCreateOrder.createOrder(orderModel)

        assertEquals(BigDecimal(4), order.total)
        verify(productRepository).findByUuidIn(listOf("uuid1"))
        verify(orderRepository).save(
            Order(
                items = listOf(
                    OrderItem(product = products[0], BigDecimal(2))
                )
            )
        )
    }
}