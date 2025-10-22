package com.umar.laundry.feature.address.data

import com.umar.laundry.feature.address.domain.model.Address

val dummyAddress = listOf(
    Address(
        name = "Umar",
        province = provinces[0],
        regency = regencies[0],
        district = districts[0],
        village = villages[0],
        street = "Jl. Jendral Sudirman No. 1",
        isPrimary = true
    ),
    Address(
        name = "Zaki",
        province = provinces[1],
        regency = regencies[1],
        district = districts[1],
        village = villages[1],
        street = "Jl. Gatot Subroto No. 2"
    ),
    Address(
        name = "Ahmad",
        province = provinces[2],
        regency = regencies[2],
        district = districts[2],
        village = villages[2],
        street = "Jl. M.H. Thamrin No. 3"
    )
)
