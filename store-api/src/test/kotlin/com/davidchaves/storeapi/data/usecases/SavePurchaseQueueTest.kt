package com.davidchaves.storeapi.data.usecases

import com.davidchaves.storeapi.data.protocols.SendToQueue
import com.davidchaves.storeapi.domain.usecases.SavePurchase
import com.davidchaves.storeapi.mock.SavePurchaseModelMock.SavePurchaseModelMock.purchaseModelWithTwoProductsMock
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [MockitoExtension::class])
class SavePurchaseQueueTest {
    @Mock
    private lateinit var sendToQueue: SendToQueue
    private lateinit var savePurchase: SavePurchase

    private val queueName: String = "purchase-queue"

    @BeforeEach
    fun setUp() {
        this.savePurchase = SavePurchaseQueue(sendToQueue, queueName)
    }

    @Test
    @DisplayName("Deve enviar compra para a fila de compras")
    fun shouldThrowIfProductsNotFound() {
        val purchase = purchaseModelWithTwoProductsMock()
        savePurchase.save(purchase)
        verify(sendToQueue, times(1)).sendToQueue(queueName, purchase)
    }

    @Test
    @DisplayName("Deve lançar erro se a fila lançar")
    fun shouldThrowIfSendToQueueThrows() {
        val purchase = purchaseModelWithTwoProductsMock()
        given(sendToQueue.sendToQueue(queueName, purchase)).willThrow(RuntimeException())
        assertThrows<RuntimeException> { savePurchase.save(purchase) }
        verify(sendToQueue, times(1)).sendToQueue(queueName, purchase)
    }
}