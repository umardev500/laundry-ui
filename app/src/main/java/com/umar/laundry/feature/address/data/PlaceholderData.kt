package com.umar.laundry.feature.address.data

import com.umar.laundry.feature.address.domain.model.District
import com.umar.laundry.feature.address.domain.model.Province
import com.umar.laundry.feature.address.domain.model.Regency
import com.umar.laundry.feature.address.domain.model.Village

// --- Placeholder Data ---
internal val provinces = listOf(
    Province(id = "1", name = "West Java"),
    Province(id = "2", name = "East Java"),
    Province(id = "3", name = "Central Java")
)

internal val regencies = listOf(
    Regency(id = "11", provinceId = "1", name = "Bandung"),
    Regency(id = "12", provinceId = "1", name = "Bogor"),
    Regency(id = "21", provinceId = "2", name = "Surabaya"),
    Regency(id = "22", provinceId = "2", name = "Malang"),
    Regency(id = "31", provinceId = "3", name = "Semarang"),
    Regency(id = "32", provinceId = "3", name = "Solo")
)

internal val districts = listOf(
    District(id = "111", regencyId = "11", name = "Coblong"),
    District(id = "112", regencyId = "11", name = "Sukajadi"),
    District(id = "211", regencyId = "21", name = "Gubeng"),
    District(id = "212", regencyId = "21", name = "Tegalsari")
)

internal val villages = listOf(
    Village(id = "1111", districtId = "111", name = "Cipaganti"),
    Village(id = "1112", districtId = "111", name = "Dago"),
    Village(id = "2111", districtId = "211", name = "Airlangga"),
    Village(id = "2112", districtId = "211", name = "Pucang Sewu")
)
