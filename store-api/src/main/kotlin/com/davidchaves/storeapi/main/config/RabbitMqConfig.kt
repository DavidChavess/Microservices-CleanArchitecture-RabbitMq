package com.davidchaves.storeapi.main.config

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableRabbit
class RabbitMqConfig {

    @Bean
    fun purchaseQueue(): Queue = Queue("purchase-queue", true)
}