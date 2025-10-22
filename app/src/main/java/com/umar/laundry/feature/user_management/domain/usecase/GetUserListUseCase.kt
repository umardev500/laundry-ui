
package com.umar.laundry.feature.user_management.domain.usecase

import com.umar.laundry.feature.user_management.domain.model.User
import com.umar.laundry.feature.user_management.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserListUseCase(private val repository: UserRepository) {
    operator fun invoke(): Flow<List<User>> = flow {
        emit(repository.getUsers())
    }
}
