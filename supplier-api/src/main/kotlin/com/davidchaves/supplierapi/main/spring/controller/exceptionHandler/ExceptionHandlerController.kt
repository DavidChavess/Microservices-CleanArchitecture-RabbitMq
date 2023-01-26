package com.davidchaves.supplierapi.main.spring.controller.exceptionHandler

import com.davidchaves.supplierapi.presentation.protocols.ErrorResponse
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [MissingKotlinParameterException::class])
    fun fieldException(exception: MissingKotlinParameterException) {
        exception.printStackTrace()
        ErrorResponse("O campo ${exception.parameter.name} é obrigatório")
    }

}