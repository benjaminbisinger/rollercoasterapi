package com.rollercoasterapi.storage.repository

import com.rollercoasterapi.domain.model.NumberPropertyValue
import com.rollercoasterapi.domain.repository.NumberPropertyValueRepository
import com.rollercoasterapi.storage.model.DBNumberPropertyValue
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
class NumberPropertyValueRepositoryImpl(private val jpaNumberPropertyValueRepository: JpaNumberPropertyValueRepository) : NumberPropertyValueRepository {

    private val mapper = NumberPropertyValueMapper()

    override fun add(numberPropertyValue: NumberPropertyValue) {
        jpaNumberPropertyValueRepository.save(mapper.map(numberPropertyValue))
    }

    override fun getForCoaster(coasterId: String): List<NumberPropertyValue> {
        return jpaNumberPropertyValueRepository.findByCoasterId(coasterId).map { mapper.map(it) }
    }

    override fun remove(numberPropertyValue: NumberPropertyValue) {
        jpaNumberPropertyValueRepository.delete(mapper.map(numberPropertyValue))
    }
}

interface JpaNumberPropertyValueRepository : JpaRepository<DBNumberPropertyValue, String> {
    fun findByCoasterId(coasterId: String): List<DBNumberPropertyValue>
}

class NumberPropertyValueMapper {

    fun map(numberPropertyValue: NumberPropertyValue): DBNumberPropertyValue {
        return DBNumberPropertyValue(
                coasterId = numberPropertyValue.coasterId,
                propertyName = numberPropertyValue.propertyName,
                value = numberPropertyValue.value
        )
    }

    fun map(numberPropertyValue: DBNumberPropertyValue): NumberPropertyValue {
        return NumberPropertyValue(
                coasterId = numberPropertyValue.coasterId,
                propertyName = numberPropertyValue.propertyName,
                value = numberPropertyValue.value
        )
    }
}