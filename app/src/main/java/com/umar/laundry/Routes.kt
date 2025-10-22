package com.umar.laundry

import cafe.adriel.voyager.core.screen.Screen
import com.umar.laundry.feature.address.domain.model.Address
import com.umar.laundry.feature.address.ui.screen.AddEditAddressScreen
import com.umar.laundry.feature.address.ui.screen.AddressListScreen
import com.umar.laundry.feature.auth.ui.screen.Login
import com.umar.laundry.feature.profile.ui.screen.ProfileScreen

object Routes {
    fun login(): Screen = Login()
    fun profile(): Screen = ProfileScreen()
    fun addressList(): Screen = AddressListScreen()
    fun addEditAddress(address: Address? = null): Screen = AddEditAddressScreen(address)
}
