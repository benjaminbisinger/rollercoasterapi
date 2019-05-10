package com.rollercoasterapi.rest.exceptions

class CoasterNotFoundException(coasterId: String) : NotFoundException("Coaster with id $coasterId")