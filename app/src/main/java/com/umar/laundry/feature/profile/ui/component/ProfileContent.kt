package com.umar.laundry.feature.profile.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.umar.laundry.R
import com.umar.laundry.core.ui.components.atoms.AppButton
import com.umar.laundry.core.ui.utils.withClickSound

enum class UserRole {
    CUSTOMER,
    ADMIN
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(userRole: UserRole) {
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

            OrderStatusSummary()

            Spacer(modifier = Modifier.height(32.dp))

            when (userRole) {
                UserRole.CUSTOMER -> CustomerMenuItems()
                UserRole.ADMIN -> AdminMenuItems()
            }

            ProfileMenuItem(R.drawable.ic_person_outlined, "Edit Profile") { /* TODO */ }
            ProfileMenuItem(R.drawable.ic_notifications_outlined, "Notifications") { /* TODO */ }
            ProfileMenuItem(R.drawable.ic_support_agent_outlined, "Help") { /* TODO */ }

            Spacer(modifier = Modifier.height(32.dp))

            AppButton(
                onClick = { /* TODO */ }.withClickSound(haptic = true),
                modifier = Modifier.fillMaxWidth(),
                text = "Logout",
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun OrderStatusSummary() {
    val statuses = listOf(
        Triple("Pending", 1, R.drawable.ic_motion_photos_paused_outlined),
        Triple("In Progress", 2, R.drawable.ic_progress_activity_outlined),
        Triple("Delivery", 0, R.drawable.ic_delivery_truck_bolt_outlined),
        Triple("Completed", 5, R.drawable.ic_check_circle_unread_outlined)
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Order Status",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "View All",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .clickable {

                        }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                statuses.forEach { (status, count, icon) ->
                    StatusChip(status = status, count = count, iconRes = icon)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StatusChip(status: String, count: Int, @DrawableRes iconRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        BadgedBox(
            badge = {
                if (count > 0) {
                    Badge { Text(count.toString()) }
                }
            }) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = status,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Text(text = status, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
private fun CustomerMenuItems() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        ProfileMenuItem(R.drawable.ic_location_on_outlined, "My Address") { /* TODO */ }
        ProfileMenuItem(R.drawable.ic_credit_card_outlined, "Payment Methods") { /* TODO */ }
    }
}

@Composable
private fun AdminMenuItems() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        ProfileMenuItem(R.drawable.ic_avg_pace_outline, "Dashboard") { /* TODO */ }
        ProfileMenuItem(R.drawable.ic_order_approve_outline, "Manage Orders") { /* TODO */ }
        ProfileMenuItem(R.drawable.ic_group_outlined, "Manage Users") { /* TODO */ }
        ProfileMenuItem(R.drawable.ic_dishwasher_gen_outlined, "Manage Machine") { /* TODO */ }
        ProfileMenuItem(
            R.drawable.ic_local_laundry_service_outlined,
            "Manage Service"
        ) { /* TODO */ }
        ProfileMenuItem(R.drawable.ic_attach_money_outlined, "Manage Payment") { /* TODO */ }
        ProfileMenuItem(R.drawable.ic_remove_moderator_outlined, "Manage RBAC") { /* TODO */ }
    }
}

@Composable
private fun ProfileHeader(
    name: String,
    email: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(72.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                        shape = CircleShape
                    ),
                contentScale = ContentScale.Crop
            )

            // Online status indicator
            Box(
                modifier = Modifier
                    .offset(x = 58.dp, y = 50.dp) // Position at bottom-right corner
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.surface,
                        shape = CircleShape
                    )
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = email,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProfileMenuItem(
    @DrawableRes icon: Int, text: String, onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick.withClickSound())
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_chevron_right),
            contentDescription = "Go to $text",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
