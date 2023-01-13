package com.davidchaves.storeapi.main.config

import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableRabbit
class RabbitMqConfig {

    @Bean
    fun purchaseQueue(): Queue = Queue("purchase-queue", true)

    @Bean
    fun notificationQueue(): Queue = Queue("notification-queue", true)

    @Bean
    fun purchaseExchange(): FanoutExchange = FanoutExchange("purchase-exchange")

    @Bean
    fun bindingPurchaseQueue(purchaseQueue: Queue, purchaseExchange: FanoutExchange): Binding =
        BindingBuilder.bind(purchaseQueue).to(purchaseExchange)

    @Bean
    fun bindingNotificationQueue(notificationQueue: Queue, purchaseExchange: FanoutExchange): Binding =
        BindingBuilder.bind(notificationQueue).to(purchaseExchange)
}