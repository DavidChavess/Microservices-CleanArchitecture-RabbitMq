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

    @Bean
    fun obaQueue(): Queue = Queue("oba-queue", true)

    @Bean
    fun aaQueue(): Queue = Queue("aa-queue", true)

    @Bean
    fun bbQueue(): Queue = Queue("bb-queue", true)
}