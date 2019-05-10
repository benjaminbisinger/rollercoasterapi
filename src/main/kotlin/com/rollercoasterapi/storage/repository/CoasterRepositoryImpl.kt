package com.rollercoasterapi.storage.repository

import com.rollercoasterapi.domain.model.Coaster
import com.rollercoasterapi.domain.repository.CoasterRepository
import com.rollercoasterapi.storage.model.DBCoaster
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
class CoasterRepositoryImpl(private val jpaCoasterRepository: JpaCoasterRepository) : CoasterRepository {

    private val mapper = CoasterMapper()

    override fun add(coaster: Coaster) {
        jpaCoasterRepository.save(mapper.map(coaster))
    }

    override fun get(id: String): Coaster {
        return mapper.map(jpaCoasterRepository.getOne(id))
    }

    override fun getAll(): List<Coaster> {
        return jpaCoasterRepository.findAll().map { mapper.map(it) }
    }

    override fun contains(coasterId: String): Boolean {
        return jpaCoasterRepository.findById(coasterId).isPresent
    }
}

interface JpaCoasterRepository : JpaRepository<DBCoaster, String>

class CoasterMapper {

    fun map(coaster: Coaster): DBCoaster {
        return DBCoaster(
                id = coaster.id,
                name = coaster.name
        )
    }

    fun map(coaster: DBCoaster): Coaster {
        return Coaster(
                id = coaster.id,
                name = coaster.name
        )
    }
}