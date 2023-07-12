package com.example.data.repository

import com.example.domain.model.CharacterPaging
import com.example.domain.model.Comic

interface CharactersRemoteDataSource {

    suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging

    suspend fun fetchComics(characterId: Int): List<Comic>
}