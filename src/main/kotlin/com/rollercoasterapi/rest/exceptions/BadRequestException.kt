package com.rollercoasterapi.rest.exceptions

abstract class BadRequestException(message: String) : ExceptionWithMessage("Bad request: $message")