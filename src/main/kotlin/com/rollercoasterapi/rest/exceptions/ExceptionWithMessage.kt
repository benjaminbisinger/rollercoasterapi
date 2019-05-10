package com.rollercoasterapi.rest.exceptions

abstract class ExceptionWithMessage(message: String) : Exception(message) {
    override val message: String get() = super.message!!
}