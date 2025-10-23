package com.umar.laundry.core.ui.components.atoms

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun UserAvatar(
    avatar: Any,
    isOnline: Boolean,
    modifier: Modifier = Modifier,
    size: Dp = 72.dp,
) {
    Box(
        modifier = modifier.size(size)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(avatar)
                .crossfade(true)
                .build(),
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
