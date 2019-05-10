package com.rollercoasterapi.domain.repository

import com.rollercoasterapi.domain.model.Coaster

interface CoasterRepository {

    fun add(coaster: Coaster)
    fun get(id: String): Coaster
    fun getAll(): List<Coaster>
    fun contains(coasterId: String): Boolean
}