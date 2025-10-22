package com.umar.laundry.feature.address.viewmodel

import android.util.Log
import cafe.adriel.voyager.core.model.ScreenModel
import com.umar.laundry.feature.address.data.districts
import com.umar.laundry.feature.address.data.provinces
import com.umar.laundry.feature.address.data.regencies
import com.umar.laundry.feature.address.data.villages
import com.umar.laundry.feature.address.domain.model.Address
import com.umar.laundry.feature.address.domain.model.District
import com.umar.laundry.feature.address.domain.model.Province
import com.umar.laundry.feature.address.domain.model.Regency
import com.umar.laundry.feature.address.domain.model.Village
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AddEditAddressUiState(
    val address: Address? = null,
    val name: String = "",
    val selectedProvince: Province? = null,
    val selectedRegency: Regency? = null,
    val selectedDistrict: District? = null,
    val selectedVillage: Village? = null,
    val street: String = "",
    val isPrimary: Boolean = false,

    val provinces: List<Province> = emptyList(),
    val regencies: List<Regency> = emptyList(),
    val districts: List<District> = emptyList(),
    val villages: List<Village> = emptyList(),

    val sheetData: SheetData? = null,
) {
    val isFormValid: Boolean
        get() = name.isNotBlank() &&
                selectedProvince != null &&
                selectedRegency != null &&
                selectedDistrict != null &&
                selectedVillage != null &&
                street.isNotBlank()
}

data class SheetData(
    val title: String,
    val items: List<Any>,
    val onItemSelected: (Any) -> Unit
)

class AddEditAddressViewModel(address: Address?) : ScreenModel {

    private val _uiState = MutableStateFlow(AddEditAddressUiState())
    val uiState: StateFlow<AddEditAddressUiState> = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                address = address,
                name = address?.name ?: "",
                selectedProvince = address?.province,
                selectedRegency = address?.regency,
                selectedDistrict = address?.district,
                selectedVillage = address?.village,
                street = address?.street ?: "",
                isPrimary = address?.isPrimary ?: false,
                provinces = provinces
            )
        }
    }

    private fun onProvinceSelected(province: Province) {
        _uiState.update { currentState ->
            if (currentState.selectedProvince?.id != province.id) {
                currentState.copy(
                    selectedProvince = province,
                    selectedRegency = null,
                    selectedDistrict = null,
                    selectedVillage = null,
                    regencies = regencies.filter { it.provinceId == province.id }
                )
            } else {
                currentState
            }
        }
    }

    private fun onRegencySelected(regency: Regency) {
        _uiState.update { currentState ->
            if (currentState.selectedRegency?.id != regency.id) {
                currentState.copy(
                    selectedRegency = regency,
                    selectedDistrict = null,
                    selectedVillage = null,
                    districts = districts.filter { it.regencyId == regency.id }
                )
            } else {
                currentState
            }
        }
    }

    private fun onDistrictSelected(district: District) {
        _uiState.update { currentState ->
            if (currentState.selectedDistrict?.id != district.id) {
                currentState.copy(
                    selectedDistrict = district,
                    selectedVillage = null,
                    villages = villages.filter { it.districtId == district.id }
                )
            } else {
                currentState
            }
        }
    }

    private fun onVillageSelected(village: Village) {
        _uiState.update { it.copy(selectedVillage = village) }
    }

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun onProvinceClick() {
        _uiState.update {
            it.copy(sheetData = SheetData("Select Province", it.provinces) { item -> onProvinceSelected(item as Province) })
        }
    }

    fun onRegencyClick() {
        _uiState.update {
            it.copy(sheetData = SheetData("Select Regency", it.regencies) { item -> onRegencySelected(item as Regency) })
        }
    }

    fun onDistrictClick() {
        _uiState.update {
            it.copy(sheetData = SheetData("Select District", it.districts) { item -> onDistrictSelected(item as District) })
        }
    }

    fun onVillageClick() {
        _uiState.update {
            it.copy(sheetData = SheetData("Select Village", it.villages) { item -> onVillageSelected(item as Village) })
        }
    }

    fun onStreetChange(street: String) {
        _uiState.update { it.copy(street = street) }
    }

    fun onPrimaryChange(isPrimary: Boolean) {
        _uiState.update { it.copy(isPrimary = isPrimary) }
    }

    fun dismissSheet() {
        _uiState.update { it.copy(sheetData = null) }
    }

    fun saveAddress() {
        val state = _uiState.value
        val address = state.address?.copy(
            name = state.name,
            province = state.selectedProvince!!,
            regency = state.selectedRegency!!,
            district = state.selectedDistrict!!,
            village = state.selectedVillage!!,
            street = state.street,
            isPrimary = state.isPrimary
        ) ?: Address(
            name = state.name,
            province = state.selectedProvince!!,
            regency = state.selectedRegency!!,
            district = state.selectedDistrict!!,
            village = state.selectedVillage!!,
            street = state.street,
            isPrimary = state.isPrimary
        )
        Log.d("AddEditAddressViewModel", "saveAddress: $address")
        // TODO: Save address to repository
    }
}
