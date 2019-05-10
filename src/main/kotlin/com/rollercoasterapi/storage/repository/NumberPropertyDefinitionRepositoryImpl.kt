package com.rollercoasterapi.storage.repository

import com.rollercoasterapi.domain.model.NumberPropertyDefinition
import com.rollercoasterapi.domain.repository.NumberPropertyDefinitionRepository
import com.rollercoasterapi.storage.model.DBNumberPropertyDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
class NumberPropertyDefinitionRepositoryImpl(private val jpaNumberPropertyDefinitionRepository: JpaNumberPropertyDefinitionRepository) : NumberPropertyDefinitionRepository {

    private val mapper = NumberPropertyDefinitionMapper()

    override fun add(numberPropertyDefinition: NumberPropertyDefinition) {
        jpaNumberPropertyDefinitionRepository.save(mapper.map(numberPropertyDefinition))
    }

    override fun get(name: String): NumberPropertyDefinition {
        return mapper.map(jpaNumberPropertyDefinitionRepository.getOne(name))
    }

    override fun getOptional(name: String): NumberPropertyDefinition? {
        return jpaNumberPropertyDefinitionRepository.findById(name).map { mapper.map(it) }.orElse(null)
    }

    override fun getAll(): List<NumberPropertyDefinition> {
        return jpaNumberPropertyDefinitionRepository.findAll().map { mapper.map(it) }
    }
}

interface JpaNumberPropertyDefinitionRepository : JpaRepository<DBNumberPropertyDefinition, String>

class NumberPropertyDefinitionMapper {

    fun map(numberPropertyDefinition: NumberPropertyDefinition): DBNumberPropertyDefinition {
        return DBNumberPropertyDefinition(
                name = numberPropertyDefinition.name,
                unit = numberPropertyDefinition.unit
        )
    }

    fun map(numberPropertyDefinition: DBNumberPropertyDefinition): NumberPropertyDefinition {
        return NumberPropertyDefinition(
                name = numberPropertyDefinition.name,
                unit = numberPropertyDefinition.unit
        )
    }
}