package com.umar.laundry.feature.profile.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.umar.laundry.R

@Composable
fun CustomerMenuItems() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        ProfileMenuItem(R.drawable.ic_location_on_outlined, "My Address") { /* TODO */ }
        ProfileMenuItem(R.drawable.ic_credit_card_outlined, "Payment Methods") { /* TODO */ }
    }
}
