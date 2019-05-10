package com.rollercoasterapi.storage.model

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity
@IdClass(DBStringPropertyValueIdClass::class)
data class DBStringPropertyValue(
        @Id val coasterId: String,
        @Id val propertyName: String,
        @Id val value: String
)

class DBStringPropertyValueIdClass(
        val coasterId: String = "",
        val propertyName: String = "",
        val value: String = ""
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DBStringPropertyValueIdClass

        if (coasterId != other.coasterId) return false
        if (propertyName != other.propertyName) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = coasterId.hashCode()
        result = 31 * result + propertyName.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }
}