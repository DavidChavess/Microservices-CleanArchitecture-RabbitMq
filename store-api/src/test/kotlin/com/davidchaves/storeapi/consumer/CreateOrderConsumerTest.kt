package com.davidchaves.storeapi.consumer

import com.davidchaves.storeapi.domain.usecases.CreateOrder
import com.davidchaves.storeapi.mock.SavePurchaseModelMock.SavePurchaseModelMock.purchaseModelWithOneProductMock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(value = [MockitoExtension::class])
class CreateOrderConsumerTest {

    @Mock
    private lateinit var createOrder: CreateOrder

    @InjectMocks
    private lateinit var createOrderConsumer: CreateOrderConsumer

    @Test
    @DisplayName("Deve chamar método de criar ordem com valores corretos")
    fun shouldCallCreateOrderWithCorrectValues() {
        val purchase = purchaseModelWithOneProductMock();
        createOrderConsumer.consume(purchase)
        verify(createOrder, times(1)).create(purchase)
    }

    @Test
    @DisplayName("Deve lançar erro se o método de criar ordem falhar")
    fun shouldThrowIfCreateOrderFails() {
        val purchase = purchaseModelWithOneProductMock();
        given(createOrder.create(purchase)).willThrow(RuntimeException())
        assertThrows<RuntimeException> { createOrderConsumer.consume(purchase) }
        verify(createOrder, times(1)).create(purchase)
    }

}