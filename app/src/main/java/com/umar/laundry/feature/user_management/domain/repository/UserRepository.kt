
package com.umar.laundry.feature.user_management.domain.repository

import com.umar.laundry.feature.user_management.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}
