package com.umar.laundry.feature.address.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.umar.laundry.feature.address.domain.model.District
import com.umar.laundry.feature.address.domain.model.Province
import com.umar.laundry.feature.address.domain.model.Regency
import com.umar.laundry.feature.address.domain.model.Village
import com.umar.laundry.feature.address.viewmodel.SheetData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegionSelectionSheet(
    sheetData: SheetData?,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onItemClick: (Any) -> Unit
) {
    val currentSheetData = sheetData ?: return

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(currentSheetData.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))

            if (currentSheetData.items.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No data available")
                }
            } else {
                LazyColumn {
                    items(currentSheetData.items) { item ->
                        val name = when (item) {
                            is Province -> item.name
                            is Regency -> item.name
                            is District -> item.name
                            is Village -> item.name
                            else -> ""
                        }
                        Text(
                            text = name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onItemClick(item) }
                                .padding(vertical = 12.dp)
                        )
                    }
                }
            }
        }
    }
}
