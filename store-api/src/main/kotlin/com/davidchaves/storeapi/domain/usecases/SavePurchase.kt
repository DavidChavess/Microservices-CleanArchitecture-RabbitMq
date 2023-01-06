package com.davidchaves.storeapi.domain.usecases

import com.davidchaves.storeapi.domain.models.Purchase
import com.davidchaves.storeapi.domain.models.SavePurchaseModel

interface SavePurchase {
    fun save(purchase: SavePurchaseModel): Purchase
}
