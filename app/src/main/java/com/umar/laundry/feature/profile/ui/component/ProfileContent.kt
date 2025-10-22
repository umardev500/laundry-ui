package com.umar.laundry.feature.profile.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.umar.laundry.R
import com.umar.laundry.Routes
import com.umar.laundry.core.ui.components.atoms.AppButton

enum class UserRole {
    CUSTOMER, ADMIN
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(userRole: UserRole) {
    val navigator = LocalNavigator.currentOrThrow
    var showLogoutDialog by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text(text = "Logout") },
            text = { Text(text = "Are you sure you want to logout?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        navigator.replaceAll(Routes.login())
                    }
                ) {
                    Text("Logout")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showLogoutDialog = false }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My Profile") })
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            ProfileHeader("Umar", "umar@example.com")
            Spacer(modifier = Modifier.height(32.dp))

            if (userRole == UserRole.CUSTOMER) {
                OrderStatusSummary()
                Spacer(modifier = Modifier.height(32.dp))
            }

            when (userRole) {
                UserRole.CUSTOMER -> CustomerMenuItems()
                UserRole.ADMIN -> AdminMenuItems()
            }

            ProfileMenuItem(R.drawable.ic_person_outlined, "Edit Profile") { /* TODO */ }
            ProfileMenuItem(R.drawable.ic_notifications_outlined, "Notifications") { /* TODO */ }
            ProfileMenuItem(R.drawable.ic_support_agent_outlined, "Help") { /* TODO */ }

            Spacer(modifier = Modifier.height(32.dp))

            AppButton(
                onClick = { showLogoutDialog = true },
                modifier = Modifier.fillMaxWidth(),
                text = "Logout",
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
