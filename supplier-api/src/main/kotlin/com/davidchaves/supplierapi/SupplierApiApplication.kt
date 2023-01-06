package com.davidchaves.supplierapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories
@SpringBootApplication
class SupplierApiApplication

fun main(args: Array<String>) {
	runApplication<SupplierApiApplication>(*args)
}
