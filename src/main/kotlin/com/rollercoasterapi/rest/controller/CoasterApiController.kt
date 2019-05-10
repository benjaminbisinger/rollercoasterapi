package com.rollercoasterapi.rest.controller

import com.rollercoasterapi.domain.model.Coaster
import com.rollercoasterapi.domain.repository.CoasterRepository
import com.rollercoasterapi.rest.model.RestCoaster
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class CoasterApiController(private val coasterRepository: CoasterRepository) {

    val mapper = CoasterMapper()

    @GetMapping("/coasters")
    fun getCoasters(): List<RestCoaster> {
        return coasterRepository.getAll().map { mapper.map(it) }
    }

    @PostMapping("/coasters")
    fun addCoaster(@RequestBody coaster: RestCoaster): RestCoaster {
        coaster.id = UUID.randomUUID().toString()
        coasterRepository.add(mapper.map(coaster))
        return coaster
    }
}

class CoasterMapper {
    fun map(coaster: Coaster): RestCoaster {
        return RestCoaster(
                id = coaster.id,
                name = coaster.name
        )
    }

    fun map(coaster: RestCoaster): Coaster {
        return Coaster(
                id = coaster.id ?: UUID.randomUUID().toString(),
                name = coaster.name
        )
    }
}