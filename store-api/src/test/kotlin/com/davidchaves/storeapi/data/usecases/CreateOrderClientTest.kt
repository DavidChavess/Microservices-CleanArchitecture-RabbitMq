package com.davidchaves.storeapi.data.usecases

import com.davidchaves.storeapi.data.protocols.SupplierClient
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
class CreateOrderClientTest {

    @Mock
    private lateinit var supplierClient: SupplierClient

    @InjectMocks
    private lateinit var createOrderClient: CreateOrderClient

    @Test
    @DisplayName("Deve chamar método de criar ordem com valores corretos")
    fun shouldCallSupplierCreateOrderWithCorrectValues() {
        val purchase = purchaseModelWithOneProductMock();
        createOrderClient.create(purchase)
        verify(supplierClient, times(1)).createOrder(purchase)
    }

    @Test
    @DisplayName("Deve lançar erro se o método de criar ordem falhar")
    fun shouldThrowIfSupplierClientThrows() {
        val purchase = purchaseModelWithOneProductMock();
        given(supplierClient.createOrder(purchase)).willThrow(RuntimeException())
        assertThrows<RuntimeException> { createOrderClient.create(purchase) }
        verify(supplierClient, times(1)).createOrder(purchase)
    }
}