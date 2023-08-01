package com.example.usecase

import com.example.data.repository.FavoritesRepository
import com.example.domain.model.Character
import com.example.usecase.base.CoroutinesDispatchers
import com.example.usecase.base.ResultStatus
import com.example.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface RemoveFavoriteUseCase {

    operator fun invoke(params: Params): Flow<ResultStatus<Unit>>

    data class Params(val characterId: Int, val name: String, val imageUrl: String)
}

class RemoveFavoriteUseCaseImpl @Inject constructor(
    private val repository: FavoritesRepository,
    private val dispatchers: CoroutinesDispatchers
) : UseCase<RemoveFavoriteUseCase.Params, Unit>(), RemoveFavoriteUseCase {

    override suspend fun doWork(params: RemoveFavoriteUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            repository.deleteFavorite(
                Character(params.characterId, params.name, params.imageUrl)
            )
            ResultStatus.Success(Unit)
        }
    }
}