package com.davidchaves.supplierapi.main.spring.controller

import com.davidchaves.supplierapi.domain.usecases.model.OrderModel
import com.davidchaves.supplierapi.presentation.protocols.Controller
import com.davidchaves.supplierapi.presentation.protocols.HttpRequest
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class CreateOrderRestController(
    @Qualifier("createOrderController")
    private val controller: Controller
) {

    @PostMapping
    fun createOrder(@RequestBody createOrderModel: OrderModel): ResponseEntity<Any> {
        val httpResponse = controller.handle(HttpRequest(body = createOrderModel))
        return ResponseEntity.status(httpResponse.statusCode).body(httpResponse.body)
    }
}