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
    @Property("\${queues.purchase.exchange}") private val exchange: String
) : SavePurchase {

    override fun save(purchase: SavePurchaseModel): Purchase {
        purchase.status = "PENDING"
        sendToQueue.sendToQueue(exchange, purchase)
        return Purchase(purchase.status!!)
    }
}