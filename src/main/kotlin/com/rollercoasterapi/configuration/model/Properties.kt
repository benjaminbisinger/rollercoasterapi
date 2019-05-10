package com.rollercoasterapi.configuration.model

data class Properties(
        val stringProperties: List<StringProperty> = emptyList(),
        val numberProperties: List<NumberProperty> = emptyList()
)

data class StringProperty(
        val name: String = "",
        val multiple: Boolean = false,
        val definedChoice: Boolean = false
)

data class NumberProperty(
        val name: String = "",
        val unit: String? = null
)