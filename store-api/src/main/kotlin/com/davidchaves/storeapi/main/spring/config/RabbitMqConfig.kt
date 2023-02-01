package com.davidchaves.storeapi.main.spring.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableRabbit
class RabbitMqConfig {

    @Bean
    fun purchaseQueue(): Queue = Queue("purchase-queue", true)

    @Bean
    fun paymentQueue(): Queue = Queue("payment-queue", true)

    @Bean
    fun notificationQueue(): Queue = Queue("notification-queue", true)

    @Bean
    fun purchaseExchange(): FanoutExchange = FanoutExchange("purchase-exchange")

    @Bean
    fun paymentExchange(): FanoutExchange = FanoutExchange("payment-exchange")

    @Bean
    fun bindingPurchaseQueue(purchaseQueue: Queue, purchaseExchange: FanoutExchange): Binding =
        BindingBuilder.bind(purchaseQueue).to(purchaseExchange)

    @Bean
    fun bindingNotificationQueue(notificationQueue: Queue, purchaseExchange: FanoutExchange): Binding =
        BindingBuilder.bind(notificationQueue).to(purchaseExchange)

    @Bean
    fun bindingNotificationQueueToPaymentExchange(notificationQueue: Queue, paymentExchange: FanoutExchange): Binding =
        BindingBuilder.bind(notificationQueue).to(paymentExchange)

    @Bean
    fun bindingPaymentQueueToPaymentExchange(paymentQueue: Queue, paymentExchange: FanoutExchange): Binding =
        BindingBuilder.bind(paymentQueue).to(paymentExchange)
}