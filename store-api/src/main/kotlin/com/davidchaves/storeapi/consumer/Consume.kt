package com.davidchaves.storeapi.consumer

import com.davidchaves.storeapi.domain.models.SavePurchaseModel
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class Consume() {

    @RabbitListener(queues = ["purchase-queue"])
    fun receive(@Payload() fileBody: SavePurchaseModel) {
        println("Message $fileBody")
    }
}