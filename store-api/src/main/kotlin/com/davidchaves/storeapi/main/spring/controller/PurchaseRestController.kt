package com.davidchaves.storeapi.main.spring.controller

import com.davidchaves.storeapi.domain.models.SavePurchaseModel
import com.davidchaves.storeapi.presentation.protocols.Controller
import com.davidchaves.storeapi.presentation.protocols.HttpRequest
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PurchaseRestController(
    @Qualifier("purchaseController")
    private val controller: Controller
) {

    @PostMapping("/purchase")
    fun save(@RequestBody body: SavePurchaseModel): ResponseEntity<Any> {
        val httpResponse = controller.handle(HttpRequest(body))
        return ResponseEntity
            .status(httpResponse.statusCode)
            .body(httpResponse.body)
    }
}