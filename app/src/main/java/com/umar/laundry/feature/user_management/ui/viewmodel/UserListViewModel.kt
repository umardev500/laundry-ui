package com.umar.laundry.feature.user_management.ui.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.umar.laundry.feature.user_management.domain.usecase.GetUserListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
) : ScreenModel {

    private val _query = MutableStateFlow("")

    private val _users = getUserListUseCase()

    val state = combine(_users, _query) { users, query ->
        UserListState(
            users = if (query.isBlank()) {
                users
            } else {
                users.filter { it.name.contains(query, ignoreCase = true) }
            },
            query = query
        )
    }.stateIn(screenModelScope, SharingStarted.WhileSubscribed(5000), UserListState())

    fun onQueryChanged(query: String) {
        _query.update { query }
    }
}
