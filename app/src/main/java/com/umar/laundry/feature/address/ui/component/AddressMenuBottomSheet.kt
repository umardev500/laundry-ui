package com.umar.laundry.feature.address.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.umar.laundry.R
import com.umar.laundry.feature.address.domain.model.Address

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressMenuBottomSheet(
    address: Address?,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onSetPrimaryClick: () -> Unit,
) {
    if (address != null) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = onDismiss,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MenuItem(text = "Edit", icon = R.drawable.ic_edit_outlined, onClick = onEditClick)
                MenuItem(text = "Delete", icon = R.drawable.ic_delete_outlined, onClick = onDeleteClick)
                if (!address.isPrimary) {
                    MenuItem(
                        text = "Set as Primary",
                        icon = R.drawable.ic_star_outlined,
                        onClick = onSetPrimaryClick
                    )
                }
            }
        }
    }
}

@Composable
private fun MenuItem(text: String, icon: Int, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(painter = painterResource(id = icon), contentDescription = null)
            Text(text = text)
        }
    }
}
