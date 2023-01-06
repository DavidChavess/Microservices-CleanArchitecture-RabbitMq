package com.davidchaves.storeapi.infra

import com.davidchaves.storeapi.data.protocols.SendToQueue
import com.davidchaves.storeapi.main.annotations.Component
import org.springframework.amqp.rabbit.core.RabbitTemplate
import java.io.Serializable

@Component
class RabbitMqQueue(
    private val rabbitTemplate: RabbitTemplate,
) : SendToQueue {

    override fun sendToQueue(queue: String, message: Serializable) {
        rabbitTemplate.convertAndSend(queue, message)
    }
}