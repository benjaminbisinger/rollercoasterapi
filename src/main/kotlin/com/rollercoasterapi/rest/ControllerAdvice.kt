package com.rollercoasterapi.rest

import com.rollercoasterapi.rest.exceptions.BadRequestException
import com.rollercoasterapi.rest.exceptions.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
internal class ControllerAdvice {

    @ResponseBody
    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFoundHandler(notFoundException: NotFoundException): String {
        return notFoundException.message
    }

    @ResponseBody
    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun basRequestHandler(badRequestException: BadRequestException): String {
        return badRequestException.message
    }
}