package com.davidchaves.storeapi.main.spring.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PurchaseExchangeConfig {
    @Bean
    fun purchaseQueue(): Queue = Queue("purchase-queue", true)

    @Bean
    fun purchaseExchange(): FanoutExchange = FanoutExchange("purchase-exchange")

    @Bean
    fun bindingPurchaseQueueToPurchaseExchange(purchaseQueue: Queue, purchaseExchange: FanoutExchange): Binding =
        BindingBuilder.bind(purchaseQueue).to(purchaseExchange)

    @Bean
    fun bindingNotificationQueueToPurchaseExchange(notificationQueue: Queue, purchaseExchange: FanoutExchange): Binding =
        BindingBuilder.bind(notificationQueue).to(purchaseExchange)
}