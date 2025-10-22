package com.umar.laundry.core.ui.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

enum class FieldSize(
    val height: Int,
    val cornerRadius: Int
) {
    Small(height = 40, cornerRadius = 8),
    Medium(height = 48, cornerRadius = 10),
    Large(height = 56, cornerRadius = 12)
}

@Composable
fun BaseField(
    modifier: Modifier = Modifier,
    size: FieldSize = FieldSize.Medium,
    isError: Boolean = false,
    isFocused: Boolean = false,
    focusedBorderColor: Color = MaterialTheme.colorScheme.primary,
    unfocusedBorderColor: Color = MaterialTheme.colorScheme.outline,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    shape: Shape = RoundedCornerShape(size.cornerRadius.dp),
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    val borderColor = when {
        isError -> MaterialTheme.colorScheme.error
        isFocused -> focusedBorderColor
        else -> unfocusedBorderColor
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = size.height.dp)
            .alpha(if (enabled) 1f else 0.6f)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shape
            )
            .background(backgroundColor, shape)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}
