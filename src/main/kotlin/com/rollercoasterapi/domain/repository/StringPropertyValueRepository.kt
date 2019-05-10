package com.rollercoasterapi.domain.repository

import com.rollercoasterapi.domain.model.StringPropertyValue

interface StringPropertyValueRepository {

    fun add(stringPropertyValue: StringPropertyValue)
    fun getForCoaster(coasterId: String): List<StringPropertyValue>
    fun getDistinctValuesForProperty(propertyName: String): Set<String>
    fun remove(stringPropertyValue: StringPropertyValue)
    fun removeForCoasterAndProperty(coasterId: String, propertyName: String)
}