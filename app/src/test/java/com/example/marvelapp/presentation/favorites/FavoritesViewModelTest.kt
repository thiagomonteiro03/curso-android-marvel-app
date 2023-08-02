package com.example.marvelapp.presentation.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.testing.MainCoroutineRule
import com.example.testing.model.CharacterFactory
import com.example.usecase.GetFavoritesUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class FavoritesViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutinesRule = MainCoroutineRule()

    private lateinit var favoritesViewModel: FavoritesViewModel

    @Mock
    private lateinit var useCase: GetFavoritesUseCase
    @Mock
    private lateinit var uiStateObserver: Observer<FavoritesViewModel.UiState>

    private val character = CharacterFactory().create(CharacterFactory.Hero.ThreeDMan)
    private val favoriteItems = listOf(
        FavoriteItem(character.id,character.name, character.imageUrl)
    )

    @Before
    fun setup(){
        favoritesViewModel  = FavoritesViewModel(
            useCase,
            mainCoroutinesRule.testDispatcherProvider
        ).apply {
            state.observeForever(uiStateObserver)
        }
    }

    @Test
    fun `should notify uiState with Success when getAll favorites`() = runTest {
        //Arrange
        whenever(useCase.invoke(any()))
            .thenReturn(
                flowOf(listOf(character))
            )
        //Act
        favoritesViewModel.getAll()
        val uiState = favoritesViewModel.state.value
        //Assert
        assertEquals(uiState, FavoritesViewModel.UiState.ShowFavorite(favoriteItems))
    }

    @Test
    fun `should notify uiState with Empty when getAll favorites`() = runTest {
        //Arrange
        whenever(useCase.invoke(any()))
            .thenReturn(
                flowOf(emptyList())
            )
        //Act
        favoritesViewModel.getAll()
        val uiState = favoritesViewModel.state.value
        //Assert
        assertEquals(uiState, FavoritesViewModel.UiState.ShowEmpty)
    }

}