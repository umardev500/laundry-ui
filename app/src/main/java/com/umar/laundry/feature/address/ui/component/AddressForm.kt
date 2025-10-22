package com.umar.laundry.feature.address.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.umar.laundry.R
import com.umar.laundry.core.ui.components.atoms.AppTextField
import com.umar.laundry.core.ui.components.atoms.SelectableField
import com.umar.laundry.feature.address.viewmodel.AddEditAddressUiState
import com.umar.laundry.feature.address.viewmodel.AddEditAddressViewModel

@Composable
internal fun AddressForm(
    uiState: AddEditAddressUiState,
    viewModel: AddEditAddressViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AppTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = uiState.name,
            onValueChange = viewModel::onNameChange,
            placeholder = stringResource(R.string.enter_recipient_name),
        )

        SelectableField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.selectedProvince?.name ?: "",
            placeholder = stringResource(R.string.select_province),
            onClick = viewModel::onProvinceClick
        )

        val enterAnimation = slideInVertically(initialOffsetY = { -it / 2 }) + fadeIn(tween(300))
        val exitAnimation = slideOutVertically(targetOffsetY = { -it / 2 }) + fadeOut(tween(300))

        AnimatedVisibility(
            visible = uiState.selectedProvince != null,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            SelectableField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.selectedRegency?.name ?: "",
                placeholder = stringResource(R.string.select_regency),
                onClick = viewModel::onRegencyClick
            )
        }

        AnimatedVisibility(
            visible = uiState.selectedRegency != null,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            SelectableField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.selectedDistrict?.name ?: "",
                placeholder = stringResource(R.string.select_district),
                onClick = viewModel::onDistrictClick
            )
        }

        AnimatedVisibility(
            visible = uiState.selectedDistrict != null,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            SelectableField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.selectedVillage?.name ?: "",
                placeholder = stringResource(R.string.select_village),
                onClick = viewModel::onVillageClick
            )
        }

        AnimatedVisibility(
            visible = uiState.selectedVillage != null,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                AppTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.street,
                    onValueChange = viewModel::onStreetChange,
                    placeholder = stringResource(R.string.enter_street),
                    singleLine = false
                )

                Row(
                    modifier = Modifier.padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = uiState.isPrimary,
                        onCheckedChange = viewModel::onPrimaryChange,
                    )
                    Text(text = stringResource(R.string.set_as_primary_address))
                }
            }
        }
    }
}
