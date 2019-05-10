package com.rollercoasterapi.rest.exceptions

class PropertyNotFoundException(propertyName: String) : NotFoundException("Property '$propertyName'")