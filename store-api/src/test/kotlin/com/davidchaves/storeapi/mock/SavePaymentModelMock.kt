package com.davidchaves.storeapi.mock;

import com.davidchaves.storeapi.domain.models.PaymentType
import com.davidchaves.storeapi.domain.models.SavePaymentCashModel
import com.davidchaves.storeapi.domain.models.SavePaymentCredModel
import com.davidchaves.storeapi.domain.models.SavePaymentModel
import java.math.BigDecimal

class SavePaymentModelMock {
    companion object SavePurchaseModelMock {
        fun savePaymentModelTypeCashMock(): SavePaymentModel =
            SavePaymentCashModel(
                purchaseId = "any purchase id",
                type = PaymentType.CASH,
                amount = BigDecimal(49.99)
            )

        fun savePaymentModelTypeCredMock(): SavePaymentModel =
            SavePaymentCredModel(
                purchaseId = "any purchase id",
                type = PaymentType.CRED,
                amount = BigDecimal(100),
                cardId = "any card id",
                cvv = 123,
                cardExpiration = "12/2020",
                numberOfInstallments = 5
            )
    }
}
