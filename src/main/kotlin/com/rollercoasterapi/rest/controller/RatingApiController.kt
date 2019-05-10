package com.rollercoasterapi.rest.controller

import com.rollercoasterapi.domain.model.CoasterRating
import com.rollercoasterapi.domain.repository.*
import com.rollercoasterapi.rest.exceptions.CoasterNotFoundException
import com.rollercoasterapi.rest.exceptions.InvalidRatingException
import com.rollercoasterapi.rest.model.*
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class RatingApiController(private val coasterRepository: CoasterRepository, private val ratingRepository: RatingRepository) {

    @PostMapping("/coasters/{coasterId}/rating")
    fun addCoasterRating(@PathVariable coasterId: String, @RequestBody rating: RestRating) {
        if (!coasterRepository.contains(coasterId)) {
            throw CoasterNotFoundException(coasterId)
        }
        if (rating.value < 0 || rating.value > 1) {
            throw InvalidRatingException(rating.value)
        }
        val userId = UUID.randomUUID().toString() // TODO, no user management yet
        ratingRepository.add(
                CoasterRating(
                        coasterId = coasterId,
                        userId = userId,
                        value = rating.value
                )
        )
    }
}