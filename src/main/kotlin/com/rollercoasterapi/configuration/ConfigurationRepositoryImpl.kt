package com.rollercoasterapi.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.rollercoasterapi.configuration.model.NumberProperty
import com.rollercoasterapi.configuration.model.Properties
import com.rollercoasterapi.configuration.model.StringProperty
import com.rollercoasterapi.domain.model.NumberPropertyDefinition
import com.rollercoasterapi.domain.model.StringPropertyDefinition
import com.rollercoasterapi.domain.repository.ConfigurationRepository
import org.springframework.stereotype.Repository

const val PROPERTIES_FILE_PATH = "/configuration/properties.json"

@Repository
class ConfigurationRepositoryImpl : ConfigurationRepository {

    val mapper = Mapper()

    override fun getAllNumberProperties(): List<NumberPropertyDefinition> {
        val inputStream = Properties::class.java.getResourceAsStream(PROPERTIES_FILE_PATH)
        return ObjectMapper()
                .readValue(inputStream, Properties::class.java)
                .numberProperties
                .map { mapper.map(it) }
    }

    override fun getAllStringProperties(): List<StringPropertyDefinition> {
        val inputStream = Properties::class.java.getResourceAsStream(PROPERTIES_FILE_PATH)
        return ObjectMapper()
                .readValue(inputStream, Properties::class.java)
                .stringProperties
                .map { mapper.map(it) }
    }
}

class Mapper {
    fun map(numberProperty: NumberProperty): NumberPropertyDefinition {
        return NumberPropertyDefinition(
                name = numberProperty.name,
                unit = numberProperty.unit
        )
    }

    fun map(stringProperty: StringProperty): StringPropertyDefinition {
        return StringPropertyDefinition(
                name = stringProperty.name,
                multiple = stringProperty.multiple,
                definedChoice = stringProperty.definedChoice
        )
    }
}