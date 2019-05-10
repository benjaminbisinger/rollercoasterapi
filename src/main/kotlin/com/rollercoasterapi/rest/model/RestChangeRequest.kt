package com.rollercoasterapi.rest.model

data class RestChangeRequest(
        val coasterId: String,
        val propertyName: String,
        val propertyValue: String,
        val removeValue: Boolean? = false,
        val addAsNewChoice: Boolean? = false
)