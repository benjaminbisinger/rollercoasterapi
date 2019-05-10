package com.rollercoasterapi.rest.controller

import com.rollercoasterapi.domain.model.NumberPropertyValue
import com.rollercoasterapi.domain.model.StringPropertyValue
import com.rollercoasterapi.domain.repository.*
import com.rollercoasterapi.rest.exceptions.CoasterNotFoundException
import com.rollercoasterapi.rest.exceptions.PropertyNotFoundException
import com.rollercoasterapi.rest.exceptions.ValueNotAValidChoiceForPropertyException
import com.rollercoasterapi.rest.exceptions.ValueNotAValidNumberException
import com.rollercoasterapi.rest.model.RestChangeRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ChangeRequestApiController(
        private val coasterRepository: CoasterRepository,
        private val numberPropertyDefinitionRepository: NumberPropertyDefinitionRepository,
        private val numberPropertyValueRepository: NumberPropertyValueRepository,
        private val stringPropertyDefinitionRepository: StringPropertyDefinitionRepository,
        private val stringPropertyValueRepository: StringPropertyValueRepository
) {

    @PostMapping("/change-request")
    fun addChangeRequest(@RequestBody changeRequest: RestChangeRequest) {
        if (!coasterRepository.contains(changeRequest.coasterId)) {
            throw CoasterNotFoundException(changeRequest.coasterId)
        }
        val numberPropertyDefinition = numberPropertyDefinitionRepository.getOptional(changeRequest.propertyName)
        if (numberPropertyDefinition != null) {
            val numberValue = try {
                changeRequest.propertyValue.toDouble()
            } catch (e: NumberFormatException) {
                throw ValueNotAValidNumberException(changeRequest.propertyName, changeRequest.propertyValue)
            }
            val numberPropertyValue = NumberPropertyValue(
                    coasterId = changeRequest.coasterId,
                    propertyName = numberPropertyDefinition.name,
                    value = numberValue
            )
            if (changeRequest.removeValue == true) {
                numberPropertyValueRepository.remove(numberPropertyValue)
            } else {
                numberPropertyValueRepository.add(numberPropertyValue)
            }
            return
        }
        val stringPropertyDefinition = stringPropertyDefinitionRepository.getOptional(changeRequest.propertyName)
        if (stringPropertyDefinition != null) {
            if (changeRequest.removeValue != true
                    && stringPropertyDefinition.definedChoice
                    && changeRequest.addAsNewChoice != true
                    && !stringPropertyValueRepository
                            .getDistinctValuesForProperty(changeRequest.propertyName)
                            .contains(changeRequest.propertyValue)
            ) {
                throw ValueNotAValidChoiceForPropertyException(changeRequest.propertyName, changeRequest.propertyValue)
            }
            val stringPropertyValue = StringPropertyValue(
                    coasterId = changeRequest.coasterId,
                    propertyName = stringPropertyDefinition.name,
                    value = changeRequest.propertyValue
            )
            if (changeRequest.removeValue == true) {
                stringPropertyValueRepository.remove(stringPropertyValue)
            } else {
                if (!stringPropertyDefinition.multiple) {
                    stringPropertyValueRepository.removeForCoasterAndProperty(changeRequest.coasterId, stringPropertyDefinition.name)
                }
                stringPropertyValueRepository.add(stringPropertyValue)
            }
            return
        }
        throw PropertyNotFoundException(changeRequest.propertyName)
    }
}