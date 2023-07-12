package com.example.marvelapp.framework

import androidx.paging.PagingSource
import com.example.data.repository.CharactersRemoteDataSource
import com.example.data.repository.CharactersRepository
import com.example.domain.model.Character
import com.example.domain.model.Comic
import com.example.marvelapp.framework.paging.CharactersPagingSource
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource
): CharactersRepository {

    override fun getCharacters(query: String): PagingSource<Int, Character> {
        return CharactersPagingSource(remoteDataSource, query)
    }

    override suspend fun getComics(characterId: Int): List<Comic> {
        return remoteDataSource.fetchComics(characterId)
    }

}