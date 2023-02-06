package com.davidchaves.storeapi.main.spring.controller

import com.davidchaves.storeapi.domain.models.SavePaymentModel
import com.davidchaves.storeapi.domain.models.SavePurchaseModel
import com.davidchaves.storeapi.presentation.protocols.Controller
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PaymentRestController(
    @Qualifier("paymentController")
    private val controller: Controller,
    private val restAdapter: RestAdapter
) {
    @PostMapping("/payment")
    fun save(@RequestBody body: SavePaymentModel): ResponseEntity<Any> =
        restAdapter.handle(controller, body)
}