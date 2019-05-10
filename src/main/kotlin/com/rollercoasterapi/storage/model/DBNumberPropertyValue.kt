package com.rollercoasterapi.storage.model

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity
@IdClass(DBNumberPropertyValueIdClass::class)
data class DBNumberPropertyValue(
        @Id val coasterId: String,
        @Id val propertyName: String,
        val value: Double
)

class DBNumberPropertyValueIdClass(
        val coasterId: String = "",
        val propertyName: String = ""
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DBNumberPropertyValueIdClass

        if (coasterId != other.coasterId) return false
        if (propertyName != other.propertyName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = coasterId.hashCode()
        result = 31 * result + propertyName.hashCode()
        return result
    }
}