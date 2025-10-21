package com.umar.laundry

import cafe.adriel.voyager.core.screen.Screen
import com.umar.laundry.feature.auth.ui.screen.Login

object Routes {
    fun login(): Screen = Login()
}