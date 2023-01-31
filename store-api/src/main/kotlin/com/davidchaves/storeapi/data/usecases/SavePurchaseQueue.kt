package com.davidchaves.storeapi.data.usecases

import com.davidchaves.storeapi.data.protocols.SendToQueue
import com.davidchaves.storeapi.domain.models.Purchase
import com.davidchaves.storeapi.domain.models.SavePurchaseModel
import com.davidchaves.storeapi.domain.usecases.SavePurchase
import com.davidchaves.storeapi.main.annotations.Component
import com.davidchaves.storeapi.main.annotations.Property
import java.util.UUID

@Component
class SavePurchaseQueue(
    private val sendToQueue: SendToQueue,
    @Property("\${queues.purchase.exchange}") private val exchange: String
) : SavePurchase {

    override fun save(purchase: SavePurchaseModel): Purchase {
        val savePurchaseModel = purchase.copy(
            id = UUID.randomUUID().toString(),
            status = "PENDING"
        )
        sendToQueue.sendToQueue(exchange, savePurchaseModel)
        return Purchase(savePurchaseModel.id!!, savePurchaseModel.status!!)
    }
}