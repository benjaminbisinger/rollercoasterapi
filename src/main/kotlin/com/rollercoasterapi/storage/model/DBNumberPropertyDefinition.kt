package com.rollercoasterapi.storage.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class DBNumberPropertyDefinition(
        @Id val name: String,
        val unit: String?
)