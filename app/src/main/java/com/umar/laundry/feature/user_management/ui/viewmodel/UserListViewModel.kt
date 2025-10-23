package com.umar.laundry.feature.user_management.ui.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.umar.laundry.feature.user_management.domain.usecase.GetUserListUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
) : ScreenModel {

    private val _query = MutableStateFlow("")

    private val _users = getUserListUseCase()

    // Debounced version for filtering
    @OptIn(FlowPreview::class)
    private val debouncedQuery = _query
        .debounce(300)
        .distinctUntilChanged()

    @OptIn(FlowPreview::class)
    val state = combine(
        _users,
        _query,
        debouncedQuery
    ) { users, query, debounced ->
        UserListState(
            query = query,
            users = if (debounced.isBlank()) {
                users
            } else {
                users.filter { it.name.contains(debounced, ignoreCase = true) }
            }
        )
    }.stateIn(screenModelScope, SharingStarted.WhileSubscribed(5000), UserListState())

    fun onQueryChanged(query: String) {
        _query.update { query }
    }
}
