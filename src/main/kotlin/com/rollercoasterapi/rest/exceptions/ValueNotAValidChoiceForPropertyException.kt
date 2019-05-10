package com.rollercoasterapi.rest.exceptions

class ValueNotAValidChoiceForPropertyException(propertyName: String, propertyValue: String)
    : BadRequestException("The value '$propertyValue' is not allowed for the property '$propertyName'. " +
        "If you want to add '$propertyValue' as a new choice for '$propertyName', set 'addAsNewChoice' = true. " +
        "This will add the value as a new choice also for every other coaster.")