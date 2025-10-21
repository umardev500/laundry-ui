package com.umar.laundry

import cafe.adriel.voyager.core.screen.Screen
import com.umar.laundry.feature.auth.ui.screen.Login
import com.umar.laundry.feature.profile.ui.screen.ProfileScreen

object Routes {
    fun login(): Screen = Login()
    fun profile(): Screen = ProfileScreen()
}
