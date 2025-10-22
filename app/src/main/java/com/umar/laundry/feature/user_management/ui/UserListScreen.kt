package com.umar.laundry.feature.user_management.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import com.umar.laundry.feature.user_management.ui.component.UserList
import com.umar.laundry.feature.user_management.ui.viewmodel.UserListViewModel

class UserListScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<UserListViewModel>()
        val state by viewModel.state.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(title = { Text("User Management") })
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                UserList(
                    users = state.users,
                    query = state.query,
                    onQueryChange = viewModel::onQueryChanged,
                    onClearQuery = { viewModel.onQueryChanged("") },
                    onItemClick = { /* ... */ },
                    onEditClick = { user -> /* TODO: Handle edit */ },
                    onDeleteClick = { },
                    onSuspendClick = { }
                )
            }
        }
    }
}
