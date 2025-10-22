package com.umar.laundry.feature.address.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.umar.laundry.feature.address.domain.model.Address
import com.umar.laundry.feature.address.ui.component.AddEditAddressContent
import com.umar.laundry.feature.address.viewmodel.AddEditAddressViewModel

class AddEditAddressScreen(private val address: Address? = null) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = rememberScreenModel { AddEditAddressViewModel(address) }
        val uiState by viewModel.uiState.collectAsState()

        AddEditAddressContent(
            uiState = uiState,
            viewModel = viewModel,
            onNavigateBack = { navigator.pop() }
        )
    }
}
