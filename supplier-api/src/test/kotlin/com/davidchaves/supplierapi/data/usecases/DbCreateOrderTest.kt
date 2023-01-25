package com.davidchaves.supplierapi.data.usecases

import com.davidchaves.supplierapi.data.exception.ProductNotFoundException
import com.davidchaves.supplierapi.data.protocols.OrderRepository
import com.davidchaves.supplierapi.data.protocols.ProductRepository
import com.davidchaves.supplierapi.domain.model.Order
import com.davidchaves.supplierapi.domain.model.OrderItem
import com.davidchaves.supplierapi.domain.model.Product
import com.davidchaves.supplierapi.domain.usecases.PurchaseItemModel
import com.davidchaves.supplierapi.domain.usecases.PurchaseModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal

@ExtendWith(value = [MockitoExtension::class])
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

        val item1 = PurchaseItemModel("uuid1", BigDecimal(2))
        val item2 = PurchaseItemModel("uuid2", BigDecimal(2))

        dbCreateOrder.createOrder(PurchaseModel("purchase id", listOf(item1, item2)))
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

        val item1 = PurchaseItemModel("uuid1", BigDecimal(2))
        val item2 = PurchaseItemModel("uuid2", BigDecimal(2))

        assertThrows<ProductNotFoundException> {
            dbCreateOrder.createOrder(PurchaseModel("purchase id", listOf(item1, item2)))
        }
        verify(productRepository).findByUuidIn(listOf("uuid1", "uuid2"))
    }

    @Test
    @DisplayName("Deve chamar salvamento de pedido com valores corretos e calcular o total dos itens")
    fun shouldCreateOrderWithCorrectValues() {
        val products = listOf(
            Product(1, "uuid1", "product1", "", BigDecimal(2)),
        )
        given(productRepository.findByUuidIn(listOf("uuid1"))).willReturn(products)

        val item = PurchaseItemModel("uuid1", BigDecimal(3))
        val purchaseModel = PurchaseModel("purchase id", listOf(item))
        val order = dbCreateOrder.createOrder(purchaseModel)

        assertEquals(BigDecimal(6), order.total)
        assertEquals(30, order.preparationTimeInMinutes)
        verify(productRepository).findByUuidIn(listOf("uuid1"))
        verify(orderRepository)
            .save(Order(
                purchaseId = "purchase id",
                items = listOf(OrderItem(product = products[0], quantity = BigDecimal(3)))
            ))
    }
}