package com.rollercoasterapi.storage.repository

import com.rollercoasterapi.domain.model.CoasterRating
import com.rollercoasterapi.domain.repository.RatingRepository
import com.rollercoasterapi.storage.model.DBCoasterRating
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
class RatingRepositoryImpl(private val jpaRatingRepository: JpaRatingRepository) : RatingRepository {

    private val mapper = RatingMapper()

    override fun add(coasterRating: CoasterRating) {
        jpaRatingRepository.save(mapper.map(coasterRating))
    }

    override fun getAllForCoaster(coasterId: String): List<CoasterRating> {
        return jpaRatingRepository.findAllByCoasterId(coasterId).map { mapper.map(it) }
    }
}

interface JpaRatingRepository : JpaRepository<DBCoasterRating, String> {
    fun findAllByCoasterId(coasterId: String): List<DBCoasterRating>
}

class RatingMapper {

    fun map(rating: CoasterRating): DBCoasterRating {
        return DBCoasterRating(
                coasterId = rating.coasterId,
                userId = rating.userId,
                value = rating.value
        )
    }

    fun map(rating: DBCoasterRating): CoasterRating {
        return CoasterRating(
                coasterId = rating.coasterId,
                userId = rating.userId,
                value = rating.value
        )
    }
}