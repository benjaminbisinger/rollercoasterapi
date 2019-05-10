package com.rollercoasterapi.storage.model

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity
@IdClass(DBCoasterRatingIdClass::class)
data class DBCoasterRating(
        @Id val coasterId: String,
        @Id val userId: String,
        val value: Double
)

class DBCoasterRatingIdClass(
        val coasterId: String = "",
        val userId: String = ""
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DBCoasterRatingIdClass

        if (coasterId != other.coasterId) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = coasterId.hashCode()
        result = 31 * result + userId.hashCode()
        return result
    }
}
