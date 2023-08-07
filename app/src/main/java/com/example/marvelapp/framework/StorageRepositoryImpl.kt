package com.example.marvelapp.framework

import com.example.data.repository.StorageLocalDataSource
import com.example.data.repository.StorageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val storageLocalDataSource: StorageLocalDataSource
): StorageRepository {
    override val sorting: Flow<String>
        get() = storageLocalDataSource.sorting

    override suspend fun saveSorting(sorting: String) {
        storageLocalDataSource.saveSorting(sorting)
    }
}