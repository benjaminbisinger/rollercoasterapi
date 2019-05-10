package com.rollercoasterapi.domain.repository

import com.rollercoasterapi.domain.model.CoasterRating

const val RATING_COUNT_PROPERTY_NAME = "Number of ratings"
const val AVERAGE_RATING_PROPERTY_NAME = "Average rating"

interface RatingRepository {

    fun add(coasterRating: CoasterRating)
    fun getAllForCoaster(coasterId: String): List<CoasterRating>
}