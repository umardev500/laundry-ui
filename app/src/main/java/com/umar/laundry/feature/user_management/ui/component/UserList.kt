package com.umar.laundry.feature.user_management.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.umar.laundry.R
import com.umar.laundry.core.ui.components.common.EmptyContent
import com.umar.laundry.core.ui.components.molecules.AppSearchBar
import com.umar.laundry.feature.user_management.domain.model.User

@Composable
fun UserList(
    users: List<User>,
    query: String,
    onQueryChange: (String) -> Unit,
    onClearQuery: () -> Unit,
    onItemClick: (User) -> Unit,
    onEditClick: (User) -> Unit,
    onDeleteClick: (User) -> Unit,
    onSuspendClick: (User) -> Unit,
) {
    LazyColumn {
        item {

            AppSearchBar(
                query = query,
                onQueryChange = onQueryChange,
                placeholder = "Search by name",
                onClear = onClearQuery
            )

            Spacer(Modifier.height(8.dp))
        }


        // 🧩 Optional: Header label
        if (users.isNotEmpty()) {
            item {
                Text(
                    text = if (query.isBlank())
                        "All Users"
                    else
                        "Results for \"${query}\" (${users.size})",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        } else {
            item {
                EmptyContent(
                    painter = painterResource(id = R.drawable.ic_group_outlined),
                    title = "No Users Found",
                    subtitle = if(query.isBlank()) "There are no users to display at the moment." else "Your search for \"${query}\" did not match any users.",
                    iconSize = 90.dp
                )
            }
        }

        // 👥 User items
        items(users) { user ->
            UserListItem(
                user = user,
                onClick = { onItemClick(user) },
                onEditClick = { onEditClick(user) },
                onDeleteClick = { onDeleteClick(user) },
                onSuspendClick = { onSuspendClick(user) }
            )
        }
    }
}
