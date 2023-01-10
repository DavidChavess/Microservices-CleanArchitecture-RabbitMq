package com.davidchaves.storeapi.data.usecases

import com.davidchaves.storeapi.data.protocols.SendToQueue
import com.davidchaves.storeapi.domain.models.Purchase
import com.davidchaves.storeapi.domain.models.SavePurchaseModel
import com.davidchaves.storeapi.domain.usecases.SavePurchase
import com.davidchaves.storeapi.main.annotations.Component

@Component
class SavePurchaseQueue(private val sendToQueue: SendToQueue) : SavePurchase {

    override fun save(purchase: SavePurchaseModel): Purchase {
        sendToQueue.sendToQueue("purchase-queue", purchase)
        return Purchase("PENDING")
    }
}