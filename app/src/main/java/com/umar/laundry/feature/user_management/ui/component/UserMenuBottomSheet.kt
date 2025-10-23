package com.umar.laundry.feature.user_management.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umar.laundry.R
import com.umar.laundry.feature.user_management.domain.model.User
import com.umar.laundry.feature.user_management.domain.model.UserStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserMenuBottomSheet(
    user: User?,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onSuspendClick: () -> Unit,
) {
    if (user != null) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = onDismiss,
            containerColor = MaterialTheme.colorScheme.surface,
            shape = RectangleShape
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MenuItem(text = "Edit", icon = R.drawable.ic_edit_outlined, onClick = onEditClick)

                val suspendText =
                    if (user.status == UserStatus.SUSPENDED) "Unsuspend" else "Suspend"
                val suspendIcon =
                    if (user.status == UserStatus.SUSPENDED) R.drawable.ic_check_circle_unread_outlined else R.drawable.ic_remove_moderator_outlined
                MenuItem(
                    text = suspendText,
                    icon = suspendIcon,
                    onClick = onSuspendClick
                )

                MenuItem(
                    text = "Delete",
                    icon = R.drawable.ic_delete_outlined,
                    onClick = onDeleteClick
                )
            }
        }
    }
}

@Composable
private fun MenuItem(text: String, icon: Int, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(painter = painterResource(id = icon), contentDescription = null)
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 16.sp)
            )
        }
    }
}
