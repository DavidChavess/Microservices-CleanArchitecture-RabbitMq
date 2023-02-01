package com.davidchaves.storeapi.presentation.controllers

import com.davidchaves.storeapi.data.exception.StoreException
import com.davidchaves.storeapi.domain.models.Purchase
import com.davidchaves.storeapi.domain.usecases.SavePurchase
import com.davidchaves.storeapi.mock.SavePurchaseModelMock.SavePurchaseModelMock.purchaseModelWithTwoProductsMock
import com.davidchaves.storeapi.presentation.protocols.HttpRequest
import com.davidchaves.storeapi.presentation.protocols.HttpResponse
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
        val purchaseRequest = purchaseModelWithTwoProductsMock()
        val purchaseResponse = Purchase("any id", "CREATED")
        given(savePurchase.save(purchaseRequest)).willReturn(purchaseResponse)
        val httpResponse = controller.handle(HttpRequest(purchaseRequest))
        verify(savePurchase, times(1)).save(purchaseRequest)
        assertEquals(HttpResponse.created(purchaseResponse), httpResponse)
    }

    @Test
    @DisplayName("Deve lançar erro se chamada para salvar compra lançar")
    fun shouldThrowIfSavePurchaseThrows() {
        val purchaseRequest = purchaseModelWithTwoProductsMock()
        val exception = StoreException(409, "", "Erro interno. Conflito")
        given(savePurchase.save(purchaseRequest)).willThrow(exception)
        val httpResponse = controller.handle(HttpRequest(purchaseRequest))
        verify(savePurchase, times(1)).save(purchaseRequest)
        assertEquals(HttpResponse.error(exception), httpResponse)
    }
}