package com.davidchaves.storeapi.consumer

import com.davidchaves.storeapi.domain.models.SavePurchaseModel
import com.davidchaves.storeapi.domain.usecases.CreateOrder
import com.davidchaves.storeapi.main.annotations.Component
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload

@Component
class CreateOrderConsumer(
    private val createOrder: CreateOrder
) {

    @RabbitListener(queues = ["purchase-queue"])
    fun receive(@Payload() purchase: SavePurchaseModel) {
        val order = createOrder.create(purchase)
        println("Message $purchase")
        println("Order $order")
    }
}