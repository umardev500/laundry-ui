package com.umar.laundry.feature.address.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.umar.laundry.R
import com.umar.laundry.feature.address.domain.model.Address

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressItem(
    address: Address,
    onEditClick: () -> Unit,
    onMenuClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEditClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = if (address.isPrimary) 0.3f else 0.1f
            )
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location_filled),
                contentDescription = null,
                modifier = Modifier.padding(top = 2.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = address.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                val fullAddress = with(address) {
                    listOfNotNull(
                        street,
                        village?.name,
                        district?.name,
                        regency?.name,
                        province?.name
                    )
                        .filter { it.isNotBlank() }
                        .joinToString(", ")
                }
                Text(
                    text = fullAddress,
                    style = MaterialTheme.typography.bodyMedium,
                )
                if (address.isPrimary) {
                    AssistChip(
                        modifier = Modifier.padding(top = 8.dp),
                        onClick = { },
                        label = { Text("Primary") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_star_filled),
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    )
                }
            }
            Box {
                IconButton(
                    onClick = onMenuClick,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more_vert),
                        contentDescription = "More options"
                    )
                }
            }
        }
    }
}
