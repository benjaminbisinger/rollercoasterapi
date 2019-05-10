package com.rollercoasterapi.storage.repository

import com.rollercoasterapi.domain.model.StringPropertyDefinition
import com.rollercoasterapi.domain.repository.StringPropertyDefinitionRepository
import com.rollercoasterapi.storage.model.DBStringPropertyDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
class StringPropertyDefinitionRepositoryImpl(private val jpaStringPropertyDefinitionRepository: JpaStringPropertyDefinitionRepository) : StringPropertyDefinitionRepository {

    private val mapper = StringPropertyDefinitionMapper()

    override fun add(stringPropertyDefinition: StringPropertyDefinition) {
        jpaStringPropertyDefinitionRepository.save(mapper.map(stringPropertyDefinition))
    }

    override fun get(name: String): StringPropertyDefinition {
        return mapper.map(jpaStringPropertyDefinitionRepository.getOne(name))
    }

    override fun getOptional(name: String): StringPropertyDefinition? {
        return jpaStringPropertyDefinitionRepository.findById(name).map { mapper.map(it) }.orElse(null)
    }

    override fun getAll(): List<StringPropertyDefinition> {
        return jpaStringPropertyDefinitionRepository.findAll().map { mapper.map(it) }
    }
}

interface JpaStringPropertyDefinitionRepository : JpaRepository<DBStringPropertyDefinition, String>

class StringPropertyDefinitionMapper {

    fun map(stringPropertyDefinition: StringPropertyDefinition): DBStringPropertyDefinition {
        return DBStringPropertyDefinition(
                name = stringPropertyDefinition.name,
                multiple = stringPropertyDefinition.multiple,
                definedChoice = stringPropertyDefinition.definedChoice
        )
    }

    fun map(stringPropertyDefinition: DBStringPropertyDefinition): StringPropertyDefinition {
        return StringPropertyDefinition(
                name = stringPropertyDefinition.name,
                multiple = stringPropertyDefinition.multiple,
                definedChoice = stringPropertyDefinition.definedChoice
        )
    }
}