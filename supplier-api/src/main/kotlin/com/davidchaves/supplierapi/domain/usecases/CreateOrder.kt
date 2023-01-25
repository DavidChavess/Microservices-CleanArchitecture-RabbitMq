package com.davidchaves.supplierapi.domain.usecases

import com.davidchaves.supplierapi.domain.model.Order
import java.math.BigDecimal

class PurchaseItemModel(
    val productUuid: String,
    val quantity: BigDecimal
)

class PurchaseModel(
    val id: String,
    val items: List<PurchaseItemModel>
)

interface CreateOrder {
    fun createOrder(purchaseModel: PurchaseModel): Order
}
