package com.umar.laundry.feature.user_management.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import com.umar.laundry.feature.user_management.domain.model.User
import com.umar.laundry.feature.user_management.ui.component.UserList
import com.umar.laundry.feature.user_management.ui.component.UserMenuBottomSheet
import com.umar.laundry.feature.user_management.ui.viewmodel.UserListViewModel
import kotlinx.coroutines.launch

class UserListScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<UserListViewModel>()
        val state by viewModel.state.collectAsState()
        val scope = rememberCoroutineScope()
        val bottomSheetState = rememberModalBottomSheetState()
        var selectedUser by rememberSaveable { mutableStateOf<User?>(null) }

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
                    onMenuClick = {
                        selectedUser = it
                        scope.launch { bottomSheetState.show() }
                    }
                )
            }
        }

        UserMenuBottomSheet(
            user = selectedUser,
            sheetState = bottomSheetState,
            onDismiss = {
                scope.launch {
                    bottomSheetState.hide()
                    selectedUser = null
                }
            },
            onEditClick = {
                // ...
                scope.launch { bottomSheetState.hide() }
            },
            onSuspendClick = {
                // ...
                scope.launch { bottomSheetState.hide() }
            },
            onDeleteClick = {
                // ...
                scope.launch { bottomSheetState.hide() }
            }
        )
    }
}
