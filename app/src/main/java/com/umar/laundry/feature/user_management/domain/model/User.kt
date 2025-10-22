package com.umar.laundry.feature.user_management.domain.model

import java.util.UUID

data class User(
    val id: UUID,
    val name: String,
    val email: String,
    val status: UserStatus,
    val roles: List<String>
)
