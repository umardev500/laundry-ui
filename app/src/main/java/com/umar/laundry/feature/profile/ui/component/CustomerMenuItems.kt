package com.umar.laundry.feature.profile.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.umar.laundry.R
import com.umar.laundry.Routes

@Composable
fun CustomerMenuItems() {
    val navigator = LocalNavigator.currentOrThrow

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        ProfileMenuItem(R.drawable.ic_location_on_outlined, "My Address") {
            navigator.push(Routes.addressList())
        }
        ProfileMenuItem(R.drawable.ic_credit_card_outlined, "Payment Methods") { /* TODO */ }
    }
}
