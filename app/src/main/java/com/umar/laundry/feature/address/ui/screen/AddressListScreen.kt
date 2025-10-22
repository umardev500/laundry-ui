package com.umar.laundry.feature.address.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.umar.laundry.Routes
import com.umar.laundry.feature.address.ui.component.AddressListContent
import com.umar.laundry.feature.address.viewmodel.AddressListViewModel

class AddressListScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = rememberScreenModel { AddressListViewModel() }
        val uiState by viewModel.uiState.collectAsState()

        AddressListContent(
            uiState = uiState,
            onNavigateBack = { navigator.pop() },
            onAddAddress = { navigator.push(Routes.addEditAddress()) },
            onEditAddress = { navigator.push(Routes.addEditAddress(it)) },
            onMenuClick = viewModel::onAddressMenuClick,
            onDismissMenu = viewModel::dismissAddressMenu,
            onDeleteAddress = viewModel::deleteAddress,
            onSetPrimaryAddress = viewModel::setPrimaryAddress
        )
    }
}
