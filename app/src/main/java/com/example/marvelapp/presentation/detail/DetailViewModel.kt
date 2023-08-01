package com.example.marvelapp.presentation.detail

import androidx.lifecycle.ViewModel
import com.example.usecase.AddFavoriteUseCase
import com.example.usecase.CheckFavoriteUseCase
import com.example.usecase.GetCharacterCategoriesUseCase
import com.example.usecase.RemoveFavoriteUseCase
import com.example.usecase.base.CoroutinesDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase,
    addFavoriteUseCase: AddFavoriteUseCase,
    removeFavoriteUseCase: RemoveFavoriteUseCase,
    checkFavoriteUseCase: CheckFavoriteUseCase,
    coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    val categories = UiActionStateLiveData(
        coroutinesDispatchers.main(),
        getCharacterCategoriesUseCase
    )

    val favorite = FavoriteUiActionStateLiveData(
        coroutinesDispatchers.main(),
        checkFavoriteUseCase,
        addFavoriteUseCase,
        removeFavoriteUseCase
    )
}