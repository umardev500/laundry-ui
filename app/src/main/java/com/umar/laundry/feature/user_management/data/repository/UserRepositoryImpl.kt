
package com.umar.laundry.feature.user_management.data.repository

import com.umar.laundry.feature.user_management.data.placeholderUsers
import com.umar.laundry.feature.user_management.domain.model.User
import com.umar.laundry.feature.user_management.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    override suspend fun getUsers(): List<User> {
        return placeholderUsers
    }
}
