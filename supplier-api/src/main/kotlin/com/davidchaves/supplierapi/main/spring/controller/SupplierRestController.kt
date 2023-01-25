package com.davidchaves.supplierapi.main.spring.controller

import com.davidchaves.supplierapi.presentation.protocols.Controller
import com.davidchaves.supplierapi.presentation.protocols.HttpRequest
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/suppliers")
class SupplierRestController(
    @Qualifier("getSupplierByStateController")
    private val controller: Controller
) {

    @GetMapping("/{state}")
    fun getByState(@PathVariable state: String): ResponseEntity<Any> {
        val httpResponse = controller.handle(HttpRequest(body = state))
        return ResponseEntity.status(httpResponse.statusCode).body(httpResponse.body)
    }
}