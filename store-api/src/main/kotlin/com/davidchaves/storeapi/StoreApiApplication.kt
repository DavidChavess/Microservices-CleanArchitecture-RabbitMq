package com.davidchaves.storeapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class StoreApiApplication

fun main(args: Array<String>) {
    runApplication<StoreApiApplication>(*args)
}
