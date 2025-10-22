package com.umar.laundry.feature.address.domain.model

data class Province(
    val id: String,
    val name: String
)

data class Regency(
    val id: String,
    val provinceId: String,
    val name: String
)

data class District(
    val id: String,
    val regencyId: String,
    val name: String
)

data class Village(
    val id: String,
    val districtId: String,
    val name: String
)
