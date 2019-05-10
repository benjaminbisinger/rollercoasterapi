package com.rollercoasterapi.domain.repository

import com.rollercoasterapi.domain.model.NumberPropertyDefinition
import com.rollercoasterapi.domain.model.StringPropertyDefinition

interface ConfigurationRepository {
    fun getAllNumberProperties(): List<NumberPropertyDefinition>
    fun getAllStringProperties(): List<StringPropertyDefinition>
}