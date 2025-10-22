package com.umar.laundry.feature.user_management.ui.viewmodel

import com.umar.laundry.feature.user_management.domain.model.User

data class UserListState(
    val users: List<User> = emptyList(),
    val query: String = "",
)
