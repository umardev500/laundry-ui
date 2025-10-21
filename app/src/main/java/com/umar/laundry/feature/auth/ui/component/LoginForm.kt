package com.umar.laundry.feature.auth.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.umar.laundry.core.ui.components.atoms.AppTextField

@Composable
fun LoginForm() {
    AppTextField(
        placeholder = "Email Address"
    )
    Spacer(Modifier.height(16.dp))
    AppTextField(
        isPassword = true,
        placeholder = "Password"
    )
}