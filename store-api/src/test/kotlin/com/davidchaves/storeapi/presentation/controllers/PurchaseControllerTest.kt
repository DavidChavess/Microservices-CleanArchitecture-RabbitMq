package com.davidchaves.storeapi.presentation.controllers

import com.davidchaves.storeapi.data.exception.StoreException
import com.davidchaves.storeapi.domain.models.Purchase
import com.davidchaves.storeapi.domain.usecases.SavePurchase
import com.davidchaves.storeapi.mock.SavePurchaseModelMock.SavePurchaseModelMock.purchaseModelWithTwoProductsMock
import com.davidchaves.storeapi.presentation.protocols.HttpRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [MockitoExtension::class])
class PurchaseControllerTest {
    @Mock
    private lateinit var savePurchase: SavePurchase

    @InjectMocks
    private lateinit var controller: PurchaseController

    @Test
    @DisplayName("Deve chamar método para salvar compra passando os valores corretos")
    fun shouldCallSavePurchaseWithCorrectValues() {
        val purchase = purchaseModelWithTwoProductsMock()
        given(savePurchase.save(purchase)).willReturn(Purchase("any id", "CREATED"))
        val httpResponse = controller.handle(HttpRequest(purchase))
        verify(savePurchase, times(1)).save(purchase)
        assertEquals(201, httpResponse.statusCode)
    }

    @Test
    @DisplayName("Deve lançar erro se chamada para salvar compra lançar")
    fun shouldThrowIfSavePurchaseThrows() {
        val purchase = purchaseModelWithTwoProductsMock()
        given(savePurchase.save(purchase)).willThrow(StoreException(500, "", "Erro interno"))
        val httpResponse = controller.handle(HttpRequest(purchase))
        verify(savePurchase, times(1)).save(purchase)
        assertEquals(500, httpResponse.statusCode)
    }
}