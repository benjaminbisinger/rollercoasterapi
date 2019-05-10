package com.rollercoasterapi.rest.model

data class RestPropertyValues(
        val numberPropertyValue: List<RestNumberPropertyValue>,
        val stringPropertyValue: List<RestStringPropertyValue>,
        val multiStringPropertyValues: List<RestMultiStringPropertyValue>
)