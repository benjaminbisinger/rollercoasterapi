package com.rollercoasterapi.domain.repository

import com.rollercoasterapi.domain.model.NumberPropertyDefinition

interface NumberPropertyDefinitionRepository {

    fun add(numberPropertyDefinition: NumberPropertyDefinition)
    fun get(name: String): NumberPropertyDefinition
    fun getOptional(name: String): NumberPropertyDefinition?
    fun getAll(): List<NumberPropertyDefinition>
}