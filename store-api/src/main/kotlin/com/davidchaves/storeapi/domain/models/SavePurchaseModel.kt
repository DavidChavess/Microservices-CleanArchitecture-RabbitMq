package com.davidchaves.storeapi.domain.models

import java.io.Serializable
import java.math.BigDecimal

data class PurchaseItemModel(
    val productUuid: String,
    val quantity: BigDecimal
) : Serializable

data class DeliveryAddressModel(
    val receiver: String,
    val address: String,
    val zipCode: String,
) : Serializable

data class SavePurchaseModel(
    val items: List<PurchaseItemModel>,
    val deliveryAddress: DeliveryAddressModel,
    var status: String?
) : Serializable