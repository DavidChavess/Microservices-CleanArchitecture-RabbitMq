package com.davidchaves.storeapi.main.spring.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PaymentExchangeConfig {
    @Bean
    fun paymentQueue(): Queue = Queue("payment-queue", true)

    @Bean
    fun paymentExchange(): FanoutExchange = FanoutExchange("payment-exchange")

    @Bean
    fun bindingNotificationQueueToPaymentExchange(notificationQueue: Queue, paymentExchange: FanoutExchange): Binding =
        BindingBuilder.bind(notificationQueue).to(paymentExchange)

    @Bean
    fun bindingPaymentQueueToPaymentExchange(paymentQueue: Queue, paymentExchange: FanoutExchange): Binding =
        BindingBuilder.bind(paymentQueue).to(paymentExchange)

}