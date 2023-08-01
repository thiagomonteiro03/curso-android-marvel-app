package com.example.marvelapp.framework.di

import com.example.usecase.AddFavoriteUseCase
import com.example.usecase.AddFavoriteUseCaseImpl
import com.example.usecase.CheckFavoriteUseCase
import com.example.usecase.CheckFavoriteUseCaseImpl
import com.example.usecase.GetCharactersUseCase
import com.example.usecase.GetCharactersUseCaseImpl
import com.example.usecase.GetCharacterCategoriesUseCase
import com.example.usecase.GetCharacterCategoriesUseCaseImpl
import com.example.usecase.RemoveFavoriteUseCase
import com.example.usecase.RemoveFavoriteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    fun bindGetCharacterCategoriesUseCase(
        useCase: GetCharacterCategoriesUseCaseImpl
    ): GetCharacterCategoriesUseCase

    @Binds
    fun bindCheckFavoriteUseCase(useCase: CheckFavoriteUseCaseImpl): CheckFavoriteUseCase

    @Binds
    fun bindAddFavoriteUseCase(useCase: AddFavoriteUseCaseImpl): AddFavoriteUseCase

    @Binds
    fun bindRemoveFavoriteUseCase(useCase: RemoveFavoriteUseCaseImpl): RemoveFavoriteUseCase
}