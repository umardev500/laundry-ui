package com.umar.laundry.feature.auth.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.umar.laundry.core.ui.components.atoms.AppButton
import com.umar.laundry.core.ui.components.atoms.ButtonSize

@Composable
fun Content() {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
        ) {
            Spacer(Modifier.height(80.dp))
            LoginHeader()
            Spacer(Modifier.height(48.dp))
            LoginForm()
            ForgotPassword()
            Spacer(Modifier.height(24.dp))
            AppButton(
                onClick = { /* TODO */ },
                modifier = Modifier.fillMaxWidth(),
                text = "Login",
                size = ButtonSize.Large
            )
            Spacer(Modifier.height(24.dp))
            OrSeparator()
            Spacer(Modifier.height(24.dp))
            GoogleLoginButton(onClick = { /* TODO */ })
            Spacer(Modifier.weight(1f))
            SignUpPrompt()
        }
    }
}
