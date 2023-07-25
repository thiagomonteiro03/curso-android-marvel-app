package com.example.data.repository

import com.example.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun getAll(): Flow<List<Character>>

    suspend fun saveFavorite(character: Character)

    suspend fun deleteFavorite(character: Character)

}