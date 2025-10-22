package com.umar.laundry.feature.user_management.data

import com.umar.laundry.feature.user_management.domain.model.User
import com.umar.laundry.feature.user_management.domain.model.UserStatus
import java.util.UUID

val placeholderUsers = listOf(
    User(id = UUID.randomUUID(), name = "John Doe", email = "user1@example.com", status = UserStatus.ACTIVE, roles = listOf("ADMIN", "MEMBER")),
    User(id = UUID.randomUUID(), name = "Jane Smith", email = "user2@example.com", status = UserStatus.SUSPENDED, roles = listOf("MEMBER")),
    User(id = UUID.randomUUID(), name = "Peter Jones", email = "user3@example.com", status = UserStatus.DELETED, roles = listOf("MEMBER")),
    User(id = UUID.randomUUID(), name = "Mary Williams", email = "user4@example.com", status = UserStatus.ACTIVE, roles = listOf("MANAGER")),
    User(id = UUID.randomUUID(), name = "David Brown", email = "user5@example.com", status = UserStatus.SUSPENDED, roles = listOf("MANAGER", "MEMBER")),
    User(id = UUID.randomUUID(), name = "Susan Garcia", email = "user6@example.com", status = UserStatus.ACTIVE, roles = listOf("MEMBER")),
    User(id = UUID.randomUUID(), name = "Michael Miller", email = "user7@example.com", status = UserStatus.ACTIVE, roles = listOf("MEMBER")),
    User(id = UUID.randomUUID(), name = "Sarah Davis", email = "user8@example.com", status = UserStatus.DELETED, roles = listOf("MEMBER")),
    User(id = UUID.randomUUID(), name = "James Rodriguez", email = "user9@example.com", status = UserStatus.ACTIVE, roles = listOf("MEMBER")),
    User(id = UUID.randomUUID(), name = "Jennifer Wilson", email = "user10@example.com", status = UserStatus.SUSPENDED, roles = listOf("MEMBER")),
    User(id = UUID.randomUUID(), name = "Robert Martinez", email = "user11@example.com", status = UserStatus.ACTIVE, roles = listOf("ADMIN")),
    User(id = UUID.randomUUID(), name = "Linda Anderson", email = "user12@example.com", status = UserStatus.ACTIVE, roles = listOf("MEMBER")),
    User(id = UUID.randomUUID(), name = "William Taylor", email = "user13@example.com", status = UserStatus.DELETED, roles = listOf("MEMBER")),
    User(id = UUID.randomUUID(), name = "Elizabeth Thomas", email = "user14@example.com", status = UserStatus.ACTIVE, roles = listOf("MANAGER")),
    User(id = UUID.randomUUID(), name = "Richard Hernandez", email = "user15@example.com", status = UserStatus.SUSPENDED, roles = listOf("MEMBER")),
    User(id = UUID.randomUUID(), name = "Jessica Moore", email = "user16@example.com", status = UserStatus.ACTIVE, roles = listOf("MEMBER")),
    User(id = UUID.randomUUID(), name = "Charles Martin", email = "user17@example.com", status = UserStatus.ACTIVE, roles = listOf("MEMBER")),
    User(id = UUID.randomUUID(), name = "Barbara Jackson", email = "user18@example.com", status = UserStatus.DELETED, roles = listOf("MEMBER")),
    User(id = UUID.randomUUID(), name = "Joseph Thompson", email = "user19@example.com", status = UserStatus.ACTIVE, roles = listOf("MEMBER")),
    User(id = UUID.randomUUID(), name = "Karen White", email = "user20@example.com", status = UserStatus.SUSPENDED, roles = listOf("MEMBER")),
)
