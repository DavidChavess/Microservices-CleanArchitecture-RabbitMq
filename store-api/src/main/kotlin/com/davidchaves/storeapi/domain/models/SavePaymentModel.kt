package com.davidchaves.storeapi.domain.models

import java.io.Serializable
import java.math.BigDecimal

enum class PaymentType {
    CRED,
    CASH
}

interface SavePaymentModel : Serializable {
    val purchaseId: String
    val amount: BigDecimal
    val type: PaymentType
}

data class SavePaymentCashModel(
    override val purchaseId: String,
    override val amount: BigDecimal,
    override val type: PaymentType
) : SavePaymentModel

data class SavePaymentCredModel(
    override val purchaseId: String,
    override val amount: BigDecimal,
    override val type: PaymentType,
    val cardId: String,
    val cvv: Int,
    val cardExpiration: String,
    val numberOfInstallments: Int
) : SavePaymentModel