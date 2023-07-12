package com.example.data.repository

import androidx.paging.PagingSource
import com.example.domain.model.Character
import com.example.domain.model.Comic

interface CharactersRepository {

    fun getCharacters(query: String): PagingSource<Int, Character>

    suspend fun getComics(characterId: Int): List<Comic>

}