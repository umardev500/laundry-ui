package com.umar.laundry.feature.user_management.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.umar.laundry.R
import com.umar.laundry.core.ui.components.atoms.UserAvatar
import com.umar.laundry.feature.user_management.domain.model.User
import com.umar.laundry.feature.user_management.domain.model.UserStatus
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListItem(
    user: User,
    onClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onSuspendClick: () -> Unit,
) {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() }
            .animateContentSize(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            UserAvatar(
                avatar = user.avatar,
                isOnline = user.status == UserStatus.ACTIVE,
                size = 56.dp
            )

            Spacer(Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(user.roles) { role ->
                        RoleBadge(role)
                    }
                    item {
                        StatusChip(status = user.status)
                    }
                }
            }

            if (user.status != UserStatus.DELETED) {
                IconButton(
                    onClick = { showBottomSheet = true },
                    modifier = Modifier
                        .size(20.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more_vert),
                        contentDescription = "Actions",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }

    if (showBottomSheet) {
        UserMenuBottomSheet(
            user = user,
            sheetState = bottomSheetState,
            onDismiss = {
                scope.launch {
                    bottomSheetState.hide()
                    showBottomSheet = false
                }
            },
            onEditClick = {
                onEditClick()
                scope.launch {
                    bottomSheetState.hide()
                    showBottomSheet = false
                }
            },
            onSuspendClick = {
                onSuspendClick()
                scope.launch {
                    bottomSheetState.hide()
                    showBottomSheet = false
                }
            },
            onDeleteClick = {
                onDeleteClick()
                scope.launch {
                    bottomSheetState.hide()
                    showBottomSheet = false
                }
            }
        )
    }
}

@Composable
fun StatusChip(status: UserStatus) {
    val color = when (status) {
        UserStatus.ACTIVE -> MaterialTheme.colorScheme.tertiary
        UserStatus.SUSPENDED -> MaterialTheme.colorScheme.secondary
        UserStatus.DELETED -> MaterialTheme.colorScheme.error
    }

    Surface(
        shape = RoundedCornerShape(50),
        color = color.copy(alpha = 0.15f),
        tonalElevation = 0.dp
    ) {
        Text(
            text = status.name.lowercase().replaceFirstChar { it.uppercase() },
            color = color,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun RoleBadge(role: String) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
        )
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(gradient)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = role,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium)
        )
    }
}
