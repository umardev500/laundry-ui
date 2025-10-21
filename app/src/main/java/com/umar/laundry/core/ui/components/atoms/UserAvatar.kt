package com.umar.laundry.core.ui.components.atoms

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun UserAvatar(
    @DrawableRes avatarRes: Int,
    isOnline: Boolean,
    modifier: Modifier = Modifier,
    size: Dp = 72.dp,
) {
    Box(
        modifier = modifier.size(size)
    ) {
        Image(
            painter = painterResource(id = avatarRes),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                    shape = CircleShape
                ),
            contentScale = ContentScale.Crop
        )

        if (isOnline) {
            val indicatorSize = size / 5f
            val indicatorBorder = size / 36f
            val offsetX = size - indicatorSize
            val offsetY = size - indicatorSize - (size / 9f)

            Box(
                modifier = Modifier
                    .offset(x = offsetX, y = offsetY)
                    .size(indicatorSize)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .border(
                        width = indicatorBorder, color = MaterialTheme.colorScheme.surface, shape = CircleShape
                    )
            )
        }
    }
}
