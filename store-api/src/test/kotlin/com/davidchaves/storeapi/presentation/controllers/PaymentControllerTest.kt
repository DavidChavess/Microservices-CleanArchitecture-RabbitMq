package com.davidchaves.storeapi.presentation.controllers

import com.davidchaves.storeapi.data.exception.StoreException
import com.davidchaves.storeapi.domain.usecases.SavePayment
import com.davidchaves.storeapi.mock.SavePaymentModelMock
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
class PaymentControllerTest {
    @Mock
    private lateinit var savePayment: SavePayment

    @InjectMocks
    private lateinit var paymentController: PaymentController

    @Test
    @DisplayName("Deve chamar método para salvar pagamento")
    fun shouldCallSavePaymentWithCorrectValues() {
        val paymentModel = SavePaymentModelMock.savePaymentModelTypeCashMock()
        val httpResponse = paymentController.handle(HttpRequest(paymentModel))
        assertEquals(HttpResponse.noContent(), httpResponse)
        verify(savePayment, times(1)).save(paymentModel)
    }

    @Test
    @DisplayName("Deve lançar erro se chamada para salvar compra lançar")
    fun shouldThrowIfSavePurchaseThrows() {
        val paymentModel = SavePaymentModelMock.savePaymentModelTypeCashMock()
        val exception = StoreException(500, "", "Erro interno")
        given(savePayment.save(paymentModel)).willThrow(exception)
        val httpResponse = paymentController.handle(HttpRequest(paymentModel))
        assertEquals(HttpResponse.error(exception), httpResponse)
        verify(savePayment, times(1)).save(paymentModel)
    }
}