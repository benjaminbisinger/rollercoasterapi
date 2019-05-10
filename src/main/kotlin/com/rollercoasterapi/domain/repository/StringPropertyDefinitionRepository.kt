package com.rollercoasterapi.domain.repository

import com.rollercoasterapi.domain.model.StringPropertyDefinition

interface StringPropertyDefinitionRepository {

    fun add(stringPropertyDefinition: StringPropertyDefinition)
    fun get(name: String): StringPropertyDefinition
    fun getOptional(name: String): StringPropertyDefinition?
    fun getAll(): List<StringPropertyDefinition>
}