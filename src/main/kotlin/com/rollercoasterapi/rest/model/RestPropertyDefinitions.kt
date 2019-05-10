package com.rollercoasterapi.rest.model

data class RestPropertyDefinitions(
        val numberPropertyDefinitions: List<RestNumberPropertyDefinition>,
        val stringPropertyDefinitions: List<RestStringPropertyDefinition>
)