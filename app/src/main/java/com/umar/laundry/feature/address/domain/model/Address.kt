package com.umar.laundry.feature.address.domain.model

import java.util.UUID

data class Address(
    val id: UUID = UUID.randomUUID(),
    val name: String = "",
    val province: Province? = null,
    val regency: Regency? = null,
    val district: District? = null,
    val village: Village? = null,
    val street: String = "",
    val isPrimary: Boolean = false
)
