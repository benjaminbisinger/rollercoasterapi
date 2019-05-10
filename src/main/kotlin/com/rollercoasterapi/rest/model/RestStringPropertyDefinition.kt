package com.rollercoasterapi.rest.model

data class RestStringPropertyDefinition(
        val name: String,
        val multiple: Boolean,
        val definedChoice: Boolean,
        val choice: Set<String>?
)