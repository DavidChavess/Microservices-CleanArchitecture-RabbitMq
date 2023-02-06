package com.davidchaves.storeapi.data.usecases

import com.davidchaves.storeapi.data.protocols.SendToQueue
import com.davidchaves.storeapi.domain.models.SavePaymentModel
import com.davidchaves.storeapi.domain.usecases.SavePayment
import com.davidchaves.storeapi.main.annotations.Usecase
import com.davidchaves.storeapi.main.annotations.Property

@Usecase
class SavePaymentQueue(
    private val sendToQueue: SendToQueue,
    @Property("\${queues.payment.exchange}") private val exchange: String
) : SavePayment {

    override fun save(savePurchaseModel: SavePaymentModel) =
        sendToQueue.sendToQueue(exchange, savePurchaseModel)
}