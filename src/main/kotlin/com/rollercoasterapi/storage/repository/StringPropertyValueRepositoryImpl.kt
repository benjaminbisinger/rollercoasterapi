package com.rollercoasterapi.storage.repository

import com.rollercoasterapi.domain.model.StringPropertyValue
import com.rollercoasterapi.domain.repository.StringPropertyValueRepository
import com.rollercoasterapi.storage.model.DBStringPropertyValue
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
class StringPropertyValueRepositoryImpl(private val jpaStringPropertyValueRepository: JpaStringPropertyValueRepository) : StringPropertyValueRepository {

    private val mapper = StringPropertyValueMapper()

    override fun add(stringPropertyValue: StringPropertyValue) {
        jpaStringPropertyValueRepository.save(mapper.map(stringPropertyValue))
    }

    override fun getForCoaster(coasterId: String): List<StringPropertyValue> {
        return jpaStringPropertyValueRepository.findByCoasterId(coasterId).map { mapper.map(it) }
    }

    override fun getDistinctValuesForProperty(propertyName: String): Set<String> {
        return jpaStringPropertyValueRepository.findByPropertyName(propertyName).map { it.value }.toSet()
    }

    override fun remove(stringPropertyValue: StringPropertyValue) {
        jpaStringPropertyValueRepository.delete(mapper.map(stringPropertyValue))
    }

    override fun removeForCoasterAndProperty(coasterId: String, propertyName: String) {
        jpaStringPropertyValueRepository.deleteByCoasterIdAndPropertyName(coasterId, propertyName)
    }
}

interface JpaStringPropertyValueRepository : JpaRepository<DBStringPropertyValue, String> {
    fun findByCoasterId(coasterId: String): List<DBStringPropertyValue>
    fun findByPropertyName(propertyName: String): List<DBStringPropertyValue>
    @Transactional
    fun deleteByCoasterIdAndPropertyName(coasterId: String, propertyName: String)
}

class StringPropertyValueMapper {

    fun map(stringPropertyValue: StringPropertyValue): DBStringPropertyValue {
        return DBStringPropertyValue(
                coasterId = stringPropertyValue.coasterId,
                propertyName = stringPropertyValue.propertyName,
                value = stringPropertyValue.value
        )
    }

    fun map(stringPropertyValue: DBStringPropertyValue): StringPropertyValue {
        return StringPropertyValue(
                coasterId = stringPropertyValue.coasterId,
                propertyName = stringPropertyValue.propertyName,
                value = stringPropertyValue.value
        )
    }
}