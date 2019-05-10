package com.rollercoasterapi.domain.repository

import com.rollercoasterapi.domain.model.NumberPropertyValue

interface NumberPropertyValueRepository {

    fun add(numberPropertyValue: NumberPropertyValue)
    fun getForCoaster(coasterId: String): List<NumberPropertyValue>
    fun remove(numberPropertyValue: NumberPropertyValue)
}