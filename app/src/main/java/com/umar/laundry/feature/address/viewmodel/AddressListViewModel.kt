package com.umar.laundry.feature.address.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import com.umar.laundry.feature.address.data.dummyAddress
import com.umar.laundry.feature.address.domain.model.Address
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AddressListUiState(
    val addresses: List<Address> = emptyList(),
    val selectedAddressForMenu: Address? = null
)

class AddressListViewModel : ScreenModel {
    private val _uiState = MutableStateFlow(AddressListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = AddressListUiState(addresses = dummyAddress)
    }

    fun onAddressMenuClick(address: Address) {
        _uiState.update { it.copy(selectedAddressForMenu = address) }
    }

    fun dismissAddressMenu() {
        _uiState.update { it.copy(selectedAddressForMenu = null) }
    }

    fun deleteAddress() {
        _uiState.update {
            val addressToDelete = it.selectedAddressForMenu ?: return@update it
            val addresses = _uiState.value.addresses.toMutableList()
            addresses.remove(addressToDelete)
            it.copy(addresses = addresses, selectedAddressForMenu = null)
        }
    }

    fun setPrimaryAddress() {
        _uiState.update {
            val addressToSetPrimary = it.selectedAddressForMenu ?: return@update it
            val addresses = _uiState.value.addresses.map {
                it.copy(isPrimary = it.id == addressToSetPrimary.id)
            }
            it.copy(addresses = addresses, selectedAddressForMenu = null)
        }
    }
}