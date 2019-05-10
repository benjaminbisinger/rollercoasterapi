package com.rollercoasterapi.storage.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class DBStringPropertyDefinition(
        @Id val name: String,
        val multiple: Boolean,
        val definedChoice: Boolean
)