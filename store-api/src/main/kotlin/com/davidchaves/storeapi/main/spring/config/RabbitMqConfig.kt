package com.davidchaves.storeapi.main.spring.config

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableRabbit
class RabbitMqConfig {
    @Bean
    fun notificationQueue(): Queue = Queue("notification-queue", true)
}