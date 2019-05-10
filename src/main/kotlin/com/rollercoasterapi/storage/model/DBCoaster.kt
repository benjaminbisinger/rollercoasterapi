package com.rollercoasterapi.storage.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class DBCoaster(
        @Id val id: String,
        val name: String
)