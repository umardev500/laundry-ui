package com.umar.laundry.core.ui.components.atoms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.umar.laundry.R
import com.umar.laundry.core.ui.utils.withClickSound

@Composable
fun SelectableField(
    value: String,
    placeholder: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: FieldSize = FieldSize.Medium,
    enabled: Boolean = true
) {
    BaseField(
        modifier = modifier.clickable(
            enabled = enabled,
            onClick = onClick.withClickSound(haptic = true)
        ),
        size = size,
        enabled = enabled
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = value.ifEmpty { placeholder },
                color = if (value.isNotEmpty()) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_drop_down),
                contentDescription = "Select"
            )
        }
    }
}
