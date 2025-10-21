package com.umar.laundry.feature.auth.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.umar.laundry.core.ui.utils.withClickSound

@Composable
fun ForgotPassword() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(onClick = { /* TODO */ }.withClickSound()) {
            Text("Forgot Password?")
        }
    }
}