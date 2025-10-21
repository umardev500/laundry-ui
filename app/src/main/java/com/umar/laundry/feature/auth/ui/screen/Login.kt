package com.umar.laundry.feature.auth.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class Login : Screen {
    @Composable
    override fun Content() {
        com.umar.laundry.feature.auth.ui.component.Content()
    }
}