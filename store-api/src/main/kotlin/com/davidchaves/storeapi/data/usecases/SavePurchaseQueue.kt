package com.davidchaves.storeapi.data.usecases

import com.davidchaves.storeapi.data.protocols.SendToQueue
import com.davidchaves.storeapi.domain.models.Purchase
import com.davidchaves.storeapi.domain.models.SavePurchaseModel
import com.davidchaves.storeapi.domain.usecases.SavePurchase
import com.davidchaves.storeapi.main.annotations.Component
import com.davidchaves.storeapi.main.annotations.Property

@Component
class SavePurchaseQueue(
    private val sendToQueue: SendToQueue,
    @Property("\${queues.purchase.name}") private val queueName: String
) : SavePurchase {

    override fun save(purchase: SavePurchaseModel): Purchase {
        sendToQueue.sendToQueue(queueName, purchase)
        return Purchase("PENDING")
    }
}