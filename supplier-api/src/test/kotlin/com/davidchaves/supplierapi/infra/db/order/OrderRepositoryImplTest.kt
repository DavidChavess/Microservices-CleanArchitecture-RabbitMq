package com.davidchaves.supplierapi.infra.db.order

import com.davidchaves.supplierapi.domain.model.Order
import com.davidchaves.supplierapi.domain.model.OrderItem
import com.davidchaves.supplierapi.domain.model.Product
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal

@ExtendWith(value = [MockitoExtension::class])
class OrderRepositoryImplTest {

    @Mock
    private lateinit var orderJpaRepository: OrderJpaRepository

    @InjectMocks
    private lateinit var orderRepositoryImpl: OrderRepositoryImpl

    @Test
    @DisplayName("Deve salvar pedido com valores corretos")
    fun shouldSaveOrderWithCorrectValues() {
        val product = Product(1, "uuid1", "any product", "any description", BigDecimal(4))
        val order = Order("purchase id", listOf(OrderItem(product, BigDecimal.TEN)))
        orderRepositoryImpl.save(order)
        verify(orderJpaRepository).save(willCalledWithThis(order))
    }

    @Test
    @DisplayName("Deve lançar erro se não conseguir salvar pedido")
    fun shouldThrowErrorIfOrderJpaRepositoryThrows() {
        val product = Product(1, "uuid1", "any product", "any description", BigDecimal(4))
        val order = Order("purchase id", listOf(OrderItem(product, BigDecimal.TEN)))
        given(orderJpaRepository.save(any()))
            .willThrow(RuntimeException())
        assertThrows<RuntimeException> { orderRepositoryImpl.save(order) }
    }

    private fun willCalledWithThis(order: Order) = argThat<OrderEntity> { o ->
        o.purchaseId == order.purchaseId &&
                o.total == order.total &&
                o.preparationTimeInMinutes == order.preparationTimeInMinutes &&
                o.items.size == order.items.size &&
                o.items[0].product.id == order.items[0].product.id
    }
}