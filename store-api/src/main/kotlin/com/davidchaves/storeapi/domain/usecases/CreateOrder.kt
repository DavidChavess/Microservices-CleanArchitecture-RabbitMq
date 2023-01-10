package com.davidchaves.storeapi.domain.usecases

import com.davidchaves.storeapi.domain.models.Order
import com.davidchaves.storeapi.domain.models.SavePurchaseModel

interface CreateOrder {
    fun create(purchase: SavePurchaseModel): Order
}
