package com.tldrandroid.rxjavamasterclass.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import com.tldrandroid.rxjavamasterclass.model.CompletablesSingleMaybesUiState
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelStateModule {

    @Provides
    @ViewModelScoped
    fun provideCompletablesSingleMaybesUiState() = MutableStateFlow(
        CompletablesSingleMaybesUiState()
    )
}
