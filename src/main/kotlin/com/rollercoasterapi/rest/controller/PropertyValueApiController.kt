package com.rollercoasterapi.rest.controller

import com.rollercoasterapi.domain.repository.*
import com.rollercoasterapi.rest.exceptions.CoasterNotFoundException
import com.rollercoasterapi.rest.model.RestMultiStringPropertyValue
import com.rollercoasterapi.rest.model.RestNumberPropertyValue
import com.rollercoasterapi.rest.model.RestPropertyValues
import com.rollercoasterapi.rest.model.RestStringPropertyValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PropertyValueApiController(private val coasterRepository: CoasterRepository,
                                 private val numberPropertyDefinitionRepository: NumberPropertyDefinitionRepository,
                                 private val numberPropertyValueRepository: NumberPropertyValueRepository,
                                 private val stringPropertyDefinitionRepository: StringPropertyDefinitionRepository,
                                 private val stringPropertyValueRepository: StringPropertyValueRepository,
                                 private val ratingRepository: RatingRepository) {

    @GetMapping("/coasters/{coasterId}/property-values")
    fun getCoasterProperties(@PathVariable coasterId: String): RestPropertyValues {
        if (!coasterRepository.contains(coasterId)) {
            throw CoasterNotFoundException(coasterId)
        }
        val restNumberPropertyValues = numberPropertyValueRepository.getForCoaster(coasterId).map { numberPropertyValue ->
            val numberPropertyDefinition = numberPropertyDefinitionRepository.get(numberPropertyValue.propertyName)
            RestNumberPropertyValue(
                    name = numberPropertyDefinition.name,
                    value = numberPropertyValue.value,
                    unit = numberPropertyDefinition.unit
            )
        }.toMutableList()
        restNumberPropertyValues.addAll(getAdditionalNumberProperties(coasterId))

        val restStringPropertyValues = mutableListOf<RestStringPropertyValue>()
        val multiStringPropertyValueMap = mutableMapOf<String, MutableList<String>>()
        stringPropertyValueRepository.getForCoaster(coasterId).forEach { stringPropertyValue ->
            val propertyDefinition = stringPropertyDefinitionRepository.get(stringPropertyValue.propertyName)
            if (!propertyDefinition.multiple) {
                restStringPropertyValues.add(RestStringPropertyValue(
                        name = propertyDefinition.name,
                        value = stringPropertyValue.value
                ))
            } else {
                if (multiStringPropertyValueMap.containsKey(propertyDefinition.name)) {
                    multiStringPropertyValueMap[propertyDefinition.name]!!.add(stringPropertyValue.value)
                } else {
                    multiStringPropertyValueMap[propertyDefinition.name] = mutableListOf(stringPropertyValue.value)
                }
            }
        }
        return RestPropertyValues(
                restNumberPropertyValues,
                restStringPropertyValues,
                multiStringPropertyValueMap.map { mapEntry ->
                    RestMultiStringPropertyValue(
                            name = mapEntry.key,
                            values = mapEntry.value
                    )
                }
        )
    }

    private fun getAdditionalNumberProperties(coasterId: String): List<RestNumberPropertyValue> {
        val coasterRatings = ratingRepository.getAllForCoaster(coasterId)
        val additionalProperties = mutableListOf(
                RestNumberPropertyValue(
                        name = RATING_COUNT_PROPERTY_NAME,
                        value = coasterRatings.size.toDouble(),
                        unit = null
                )
        )
        if (coasterRatings.isNotEmpty()) {
            additionalProperties.add(
                    RestNumberPropertyValue(
                            name = AVERAGE_RATING_PROPERTY_NAME,
                            value = coasterRatings.map { it.value }.average(),
                            unit = null
                    )
            )
        }
        return additionalProperties
    }
}