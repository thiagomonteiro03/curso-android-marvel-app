package com.example.marvelapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Comic
import com.example.domain.model.Event
import com.example.marvelapp.R
import com.example.usecase.GetCharacterCategoriesUseCase
import com.example.usecase.base.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    fun getCharacterCategories(characterId: Int) = viewModelScope.launch {
        getCharacterCategoriesUseCase(GetCharacterCategoriesUseCase.GetComicsParams(characterId))
            .watchStatus()
    }

    private suspend fun Flow<ResultStatus<Pair<List<Comic>, List<Event>>>>.watchStatus() {
        collect { status ->
            _uiState.value = when (status){
                ResultStatus.Loading -> UiState.Loading
                is ResultStatus.Success -> {
                    val detailParentList = mutableListOf<DetailParentVE>()

                    val comics = status.data.first
                    if (comics.isNotEmpty()) {
                        comics.map {
                            DetailChildVE(it.id, it.imageUrl)
                        }.also {
                            detailParentList.add(
                                DetailParentVE(R.string.details_comics_category, it)
                            )
                        }
                    }

                    val events = status.data.second
                    if (events.isNotEmpty()) {
                        events.map {
                            DetailChildVE(it.id, it.imageUrl)
                        }.also {
                            detailParentList.add(
                                DetailParentVE(R.string.details_events_category, it)
                            )
                        }
                    }

                    if (detailParentList.isEmpty())
                        UiState.Empty
                    else
                        UiState.Success(detailParentList)
                }
                is ResultStatus.Error -> UiState.Error
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val detailParentList: List<DetailParentVE>) : UiState()
        object Error : UiState()
        object Empty : UiState()
    }
}