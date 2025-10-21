package com.umar.laundry.feature.profile.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.umar.laundry.R

@Composable
fun AdminMenuItems() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        ProfileMenuItem(R.drawable.ic_avg_pace_outline, "Dashboard") { /* TODO */ }
        ProfileMenuItem(R.drawable.ic_order_approve_outline, "Manage Orders") { /* TODO */ }
        ProfileMenuItem(R.drawable.ic_group_outlined, "Manage Users") { /* TODO */ }
        ProfileMenuItem(R.drawable.ic_dishwasher_gen_outlined, "Manage Machine") { /* TODO */ }
        ProfileMenuItem(
            R.drawable.ic_local_laundry_service_outlined, "Manage Service"
        ) { /* TODO */ }
        ProfileMenuItem(R.drawable.ic_attach_money_outlined, "Manage Payment") { /* TODO */ }
        ProfileMenuItem(R.drawable.ic_remove_moderator_outlined, "Manage RBAC") { /* TODO */ }
    }
}
