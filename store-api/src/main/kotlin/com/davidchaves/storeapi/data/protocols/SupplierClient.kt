package com.davidchaves.storeapi.data.protocols

import com.davidchaves.storeapi.domain.models.Order
import com.davidchaves.storeapi.domain.models.SavePurchaseModel

interface SupplierClient {
    fun create(purchase: SavePurchaseModel): Order
}
