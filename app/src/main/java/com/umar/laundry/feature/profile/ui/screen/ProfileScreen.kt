package com.umar.laundry.feature.profile.ui.screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.umar.laundry.feature.profile.ui.component.ProfileContent
import com.umar.laundry.feature.profile.ui.component.UserRole

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        // In a real app, you would get this role from a ViewModel or user state
        val userRole = UserRole.ADMIN

        ProfileContent(userRole = userRole)
    }
}
