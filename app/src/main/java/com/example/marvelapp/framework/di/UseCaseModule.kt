package com.example.marvelapp.framework.di

import com.example.usecase.GetCharactersUseCase
import com.example.usecase.GetCharactersUseCaseImpl
import com.example.usecase.GetComicsUseCase
import com.example.usecase.GetComicsUseCaseImpl
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
    fun bindGetComicsUseCase(useCase: GetComicsUseCaseImpl): GetComicsUseCase
}