package com.umar.laundry.core.ui.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umar.laundry.R
import com.umar.laundry.core.ui.utils.withClickSound


// --- Size presets ------------------------------------------------------------
enum class TextFieldSize(
    val height: Int, val textSize: Int, val iconSize: Int, val cornerRadius: Int
) {
    Small(height = 40, textSize = 14, iconSize = 18, cornerRadius = 8), Medium(
        height = 48, textSize = 16, iconSize = 20, cornerRadius = 10
    ),
    Large(height = 56, textSize = 18, iconSize = 24, cornerRadius = 12)
}


// --- Component ---------------------------------------------------------------
@Composable
fun AppTextField(
    placeholder: String? = null,
    isPassword: Boolean = false,
    isError: Boolean = false,
    size: TextFieldSize = TextFieldSize.Medium,
    focusedBorderColor: Color = MaterialTheme.colorScheme.primary,
    unfocusedBorderColor: Color = MaterialTheme.colorScheme.outline,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    shape: Shape = RoundedCornerShape(size.cornerRadius.dp)
) {
    var text by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }
    val colors = MaterialTheme.colorScheme

    val textStyle = TextStyle(
        fontSize = size.textSize.sp,
        lineHeight = size.textSize.sp * 1.2f, // explicitly set line height
        color = colors.onSurface
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(size.height.dp)
                .border(
                    width = 1.dp, color = when {
                        isError -> MaterialTheme.colorScheme.error
                        isFocused -> focusedBorderColor
                        else -> unfocusedBorderColor
                    }, shape = shape
                )
                .background(backgroundColor, shape)
                .padding(horizontal = 12.dp)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }, verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                textStyle = textStyle,
                visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            if (text.isEmpty() && !placeholder.isNullOrEmpty()) {
                                Text(
                                    text = placeholder,
                                    style = textStyle.copy(color = colors.onSurfaceVariant)
                                )
                            }
                            innerTextField()
                        }

                        if (isPassword) {
                            IconButton(
                                onClick = { passwordVisible = !passwordVisible }.withClickSound(haptic = true),
                                modifier = Modifier.size(size.iconSize.dp)
                            ) {
                                Icon(
                                    painter = painterResource(if (passwordVisible) R.drawable.ms_visibility else R.drawable.visibility_off),
                                    contentDescription = "Visibility Icon",
                                )
                            }
                        }
                    }
                })
        }
    }
}
