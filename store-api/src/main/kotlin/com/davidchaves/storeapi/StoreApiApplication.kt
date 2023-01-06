package com.davidchaves.storeapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StoreApiApplication

fun main(args: Array<String>) {
    runApplication<StoreApiApplication>(*args)
}
