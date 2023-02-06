package com.davidchaves.storeapi.domain.models

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.io.Serializable
import java.math.BigDecimal

enum class PaymentType {
    CRED,
    CASH
}

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
    value = [
        JsonSubTypes.Type(name = "CASH", value = SavePaymentCashModel::class),
        JsonSubTypes.Type(name = "CRED", value = SavePaymentCredModel::class)
    ]
)
interface SavePaymentModel : Serializable {
    val purchaseId: String
    val amount: BigDecimal
    fun type(): PaymentType
}

data class SavePaymentCashModel(
    override val purchaseId: String,
    override val amount: BigDecimal,
) : SavePaymentModel {
    override fun type(): PaymentType = PaymentType.CASH
}

data class SavePaymentCredModel(
    override val purchaseId: String,
    override val amount: BigDecimal,
    val cardId: String,
    val cvv: Int,
    val cardExpiration: String,
    val numberOfInstallments: Int
) : SavePaymentModel {
    override fun type(): PaymentType = PaymentType.CRED
}