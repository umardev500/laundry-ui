package com.umar.laundry.core.ui.components.atoms

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umar.laundry.core.ui.utils.withClickSound

enum class ButtonSize(
    val height: Int,
    val cornerRadius: Int,
    val textSize: Int
) {
    Small(height = 40, cornerRadius = 8, textSize = 14),
    Medium(height = 48, cornerRadius = 10, textSize = 16),
    Large(height = 56, cornerRadius = 12, textSize = 18)
}

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    size: ButtonSize = ButtonSize.Medium,
) {
    Button(
        modifier = modifier.height(size.height.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(size.cornerRadius.dp),
        onClick = onClick.withClickSound(haptic = true)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = size.textSize.sp
        )
    }
}
