package com.umar.laundry.feature.auth.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.umar.laundry.core.ui.components.atoms.AppTextField

@Composable
fun LoginForm() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    AppTextField(
        value = email,
        onValueChange = { email = it },
        placeholder = "Email Address"
    )
    Spacer(Modifier.height(16.dp))
    AppTextField(
        value = password,
        onValueChange = { password = it },
        isPassword = true,
        placeholder = "Password"
    )
}