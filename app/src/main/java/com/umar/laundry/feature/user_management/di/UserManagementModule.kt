package com.umar.laundry.feature.user_management.di

import com.umar.laundry.feature.user_management.data.repository.UserRepositoryImpl
import com.umar.laundry.feature.user_management.domain.repository.UserRepository
import com.umar.laundry.feature.user_management.domain.usecase.GetUserListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserManagementModule {

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideGetUserListUseCase(repository: UserRepository): GetUserListUseCase {
        return GetUserListUseCase(repository)
    }
}
