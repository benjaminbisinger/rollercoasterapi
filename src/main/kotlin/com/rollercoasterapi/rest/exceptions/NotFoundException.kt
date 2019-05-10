package com.rollercoasterapi.rest.exceptions

abstract class NotFoundException(resourceName: String) : ExceptionWithMessage("$resourceName was not found")