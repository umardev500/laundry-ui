package com.umar.laundry.feature.user_management.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.umar.laundry.feature.user_management.ui.viewmodel.UserListViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityRetainedComponent::class)
interface ViewModelModule {
    @Binds
    @IntoMap
    @ScreenModelKey(UserListViewModel::class)
    fun bindUserListViewModel(viewModel: UserListViewModel): ScreenModel
}
