package com.rollercoasterapi.rest.exceptions

class ValueNotAValidNumberException(propertyName: String, propertyValue: String)
    : BadRequestException("The value '$propertyValue' is not a valid value for the property '$propertyName'. Only Numbers are allowed.")