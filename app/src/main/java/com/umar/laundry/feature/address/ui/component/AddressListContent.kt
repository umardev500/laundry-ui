package com.umar.laundry.feature.address.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.umar.laundry.R
import com.umar.laundry.core.ui.components.common.EmptyContent
import com.umar.laundry.core.ui.utils.withClickSound
import com.umar.laundry.feature.address.domain.model.Address
import com.umar.laundry.feature.address.viewmodel.AddressListUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressListContent(
    uiState: AddressListUiState,
    onNavigateBack: () -> Unit,
    onAddAddress: () -> Unit,
    onEditAddress: (Address) -> Unit,
    onMenuClick: (Address) -> Unit,
    onDismissMenu: () -> Unit,
    onDeleteAddress: () -> Unit,
    onSetPrimaryAddress: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(uiState.selectedAddressForMenu) {
        if (uiState.selectedAddressForMenu != null) {
            sheetState.show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.address_list_title)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack.withClickSound()) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = stringResource(R.string.back_content_description)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddAddress.withClickSound()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = stringResource(R.string.add_address_content_description)
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (uiState.addresses.isEmpty()) {
                EmptyContent(
                    painter = painterResource(id = R.drawable.ic_location_off_filled),
                    title = stringResource(R.string.empty_address_title),
                    subtitle = stringResource(R.string.empty_address_subtitle)
                )
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.addresses) { address ->
                        AddressItem(
                            address = address,
                            onEditClick = { onEditAddress(address) }.withClickSound(),
                            onMenuClick = { onMenuClick(address) }.withClickSound(),
                        )
                    }
                }
            }
        }
    }

    AddressMenuBottomSheet(
        address = uiState.selectedAddressForMenu,
        sheetState = sheetState,
        onDismiss = onDismissMenu,
        onEditClick = {
            uiState.selectedAddressForMenu?.let { onEditAddress(it) }
            onDismissMenu()
        }.withClickSound(),
        onDeleteClick = onDeleteAddress.withClickSound(),
        onSetPrimaryClick = onSetPrimaryAddress.withClickSound()
    )
}
