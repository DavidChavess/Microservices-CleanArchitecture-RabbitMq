package com.davidchaves.storeapi.main.spring.controller

import com.davidchaves.storeapi.main.annotations.Component
import com.davidchaves.storeapi.presentation.protocols.Controller
import com.davidchaves.storeapi.presentation.protocols.HttpRequest
import org.springframework.http.ResponseEntity

@Component
class RestAdapter {
    fun handle(controller: Controller, body: Any): ResponseEntity<Any> {
        val httpResponse = controller.handle(HttpRequest(body))
        return ResponseEntity
            .status(httpResponse.statusCode)
            .body(httpResponse.body)
    }
}
