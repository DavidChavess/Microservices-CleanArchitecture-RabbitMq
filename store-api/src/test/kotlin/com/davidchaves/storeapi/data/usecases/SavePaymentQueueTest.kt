package com.davidchaves.storeapi.data.usecases

import com.davidchaves.storeapi.data.protocols.SendToQueue
import com.davidchaves.storeapi.domain.usecases.SavePayment
import com.davidchaves.storeapi.mock.SavePaymentModelMock.SavePurchaseModelMock.savePaymentModelTypeCashMock
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [MockitoExtension::class])
class SavePaymentQueueTest {
    @Mock
    private lateinit var sendToQueue: SendToQueue
    private lateinit var savePayment: SavePayment
    private val exchange: String = "payment"

    @BeforeEach
    fun setUp() {
        this.savePayment = SavePaymentQueue(sendToQueue, exchange)
    }

    @Test
    @DisplayName("Deve salvar pagamento na fila/exchange de pagamentos")
    fun shouldSavePaymentInQueue() {
        val savePaymentModel = savePaymentModelTypeCashMock()
        savePayment.save(savePaymentModel)
        verify(sendToQueue).sendToQueue(exchange, savePaymentModel)
    }

    @Test
    @DisplayName("Deve lançar erro se a fila lançar")
    fun shouldThrowIfSendToQueueThrows() {
        val savePaymentModel = savePaymentModelTypeCashMock()
        given(sendToQueue.sendToQueue(exchange, savePaymentModel)).willThrow(IndexOutOfBoundsException())
        assertThrows<IndexOutOfBoundsException> { savePayment.save(savePaymentModel) }
        verify(sendToQueue).sendToQueue(exchange, savePaymentModel)
    }
}