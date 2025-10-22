package com.umar.laundry.feature.address.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.umar.laundry.core.ui.components.atoms.AppButton
import com.umar.laundry.core.ui.utils.withClickSound
import com.umar.laundry.feature.address.viewmodel.AddEditAddressUiState
import com.umar.laundry.feature.address.viewmodel.AddEditAddressViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditAddressContent(
    uiState: AddEditAddressUiState,
    viewModel: AddEditAddressViewModel,
    onNavigateBack: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    LaunchedEffect(uiState.sheetData) {
        if (uiState.sheetData != null) {
            sheetState.show()
        }
    }

    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = {
            TopAppBar(
                title = {
                    val title = if (uiState.address == null) {
                        stringResource(R.string.add_address_title)
                    } else {
                        stringResource(R.string.edit_address_title)
                    }
                    Text(title)
                },
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
        bottomBar = {
            BottomAppBar {
                AppButton(
                    onClick = {
                        viewModel.saveAddress()
                        onNavigateBack()
                    }.withClickSound(haptic = true),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = stringResource(R.string.save_address_button),
                    enabled = uiState.isFormValid
                )
            }
        }
    ) { paddingValues ->
        AddressForm(
            uiState = uiState,
            viewModel = viewModel,
            modifier = Modifier.padding(paddingValues)
        )
    }

    RegionSelectionSheet(
        sheetData = uiState.sheetData,
        sheetState = sheetState,
        onDismiss = viewModel::dismissSheet,
        onItemClick = {
            uiState.sheetData?.onItemSelected?.invoke(it)
            viewModel.dismissSheet()
        }
    )
}
