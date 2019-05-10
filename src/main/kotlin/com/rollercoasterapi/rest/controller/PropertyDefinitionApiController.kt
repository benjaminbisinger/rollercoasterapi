package com.rollercoasterapi.rest.controller

import com.rollercoasterapi.domain.model.NumberPropertyDefinition
import com.rollercoasterapi.domain.model.StringPropertyDefinition
import com.rollercoasterapi.domain.repository.NumberPropertyDefinitionRepository
import com.rollercoasterapi.domain.repository.StringPropertyDefinitionRepository
import com.rollercoasterapi.domain.repository.StringPropertyValueRepository
import com.rollercoasterapi.rest.model.RestNumberPropertyDefinition
import com.rollercoasterapi.rest.model.RestPropertyDefinitions
import com.rollercoasterapi.rest.model.RestStringPropertyDefinition
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PropertyDefinitionApiController(private val numberPropertyDefinitionRepository: NumberPropertyDefinitionRepository,
                                      private val stringPropertyDefinitionRepository: StringPropertyDefinitionRepository,
                                      private val stringPropertyValueRepository: StringPropertyValueRepository) {

    val numberPropertyDefinitionMapper = NumberPropertyDefinitionMapper()
    val stringPropertyDefinitionMapper = StringPropertyDefinitionMapper()

    @GetMapping("/property-definitions")
    fun getPropertyDefinitions(): RestPropertyDefinitions {
        val numberPropertyDefinitions = numberPropertyDefinitionRepository.getAll().map {
            numberPropertyDefinitionMapper.map(it)
        }
        val stringPropertyDefinitions = stringPropertyDefinitionRepository.getAll().map { propertyDefinition ->
            val choice = if (propertyDefinition.definedChoice) {
                stringPropertyValueRepository.getDistinctValuesForProperty(propertyDefinition.name)
            } else null
            stringPropertyDefinitionMapper.map(propertyDefinition, choice)
        }
        return RestPropertyDefinitions(
            numberPropertyDefinitions = numberPropertyDefinitions,
            stringPropertyDefinitions = stringPropertyDefinitions
        )
    }
}

class NumberPropertyDefinitionMapper {
    
    fun map (numberPropertyDefinition: NumberPropertyDefinition): RestNumberPropertyDefinition {
        return RestNumberPropertyDefinition(
                name = numberPropertyDefinition.name,
                unit = numberPropertyDefinition.unit
        )
    }
}

class StringPropertyDefinitionMapper {
    fun map (stringPropertyDefinition: StringPropertyDefinition, choice: Set<String>?): RestStringPropertyDefinition {
        return RestStringPropertyDefinition(
                name = stringPropertyDefinition.name,
                multiple = stringPropertyDefinition.multiple,
                definedChoice = stringPropertyDefinition.definedChoice,
                choice = choice
        )
    }
}