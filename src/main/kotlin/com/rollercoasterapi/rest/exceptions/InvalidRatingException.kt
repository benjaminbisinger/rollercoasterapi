package com.rollercoasterapi.rest.exceptions

class InvalidRatingException(rating: Double) : BadRequestException("$rating is not a valid rating. Only Numbers between 0 and 1 are allowed.")