package com.davidchaves.storeapi.data.usecases

import com.davidchaves.storeapi.data.protocols.SendToQueue
import com.davidchaves.storeapi.domain.usecases.SavePurchase
import com.davidchaves.storeapi.mock.SavePurchaseModelMock.SavePurchaseModelMock.purchaseModelWithTwoProductsMock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [MockitoExtension::class])
class SavePurchaseQueueTest {
    @Mock
    private lateinit var sendToQueue: SendToQueue
    private lateinit var savePurchase: SavePurchase

    private val exchange: String = "purchase"

    @BeforeEach
    fun setUp() {
        this.savePurchase = SavePurchaseQueue(sendToQueue, exchange)
    }

    @Test
    @DisplayName("Deve salvar compra com sucesso")
    fun shouldSavePurchase() {
        val purchase = purchaseModelWithTwoProductsMock()
        val purchaseResponse = savePurchase.save(purchase)
        assertEquals("PENDING", purchaseResponse.status)

    }

    @Test
    @DisplayName("Deve lançar erro se a fila lançar")
    fun shouldThrowIfSendToQueueThrows() {
        val purchase = purchaseModelWithTwoProductsMock()
        given(sendToQueue.sendToQueue(exchange, purchase)).willThrow(RuntimeException())
        assertThrows<RuntimeException> { savePurchase.save(purchase) }
    }
}