package com.example.data.mapper

import com.example.data.StorageConstants
import com.example.domain.model.SortingType
import javax.inject.Inject

class SortingMapper @Inject constructor() {

    fun mapToPair(sorting: String): Pair<String, String> {
        val nameAscending = SortingType.ORDER_BY_NAME.value to SortingType.ORDER_ASCENDING.value
        val nameDescending = SortingType.ORDER_BY_NAME.value to SortingType.ORDER_DESCENDING.value
        val modifiedAscending = SortingType.ORDER_BY_MODIFIED.value to SortingType.ORDER_ASCENDING.value
        val modifiedDescending = SortingType.ORDER_BY_MODIFIED.value to SortingType.ORDER_DESCENDING.value

        return when (sorting) {
            StorageConstants.ORDER_BY_NAME_ASCENDING -> nameAscending
            StorageConstants.ORDER_BY_NAME_DESCENDING -> nameDescending
            StorageConstants.ORDER_BY_MODIFIED_ASCENDING -> modifiedAscending
            StorageConstants.ORDER_BY_MODIFIED_DESCENDING -> modifiedDescending
            else -> nameAscending
        }
    }

    fun mapFromPair(sortingPair: Pair<String, String>): String {

        return when (sortingPair) {
            SortingType.ORDER_BY_NAME.value to SortingType.ORDER_ASCENDING.value ->
                StorageConstants.ORDER_BY_NAME_ASCENDING
            SortingType.ORDER_BY_NAME.value to SortingType.ORDER_DESCENDING.value ->
                StorageConstants.ORDER_BY_NAME_DESCENDING
            SortingType.ORDER_BY_MODIFIED.value to SortingType.ORDER_ASCENDING.value ->
                StorageConstants.ORDER_BY_MODIFIED_ASCENDING
            SortingType.ORDER_BY_MODIFIED.value to SortingType.ORDER_DESCENDING.value ->
                StorageConstants.ORDER_BY_MODIFIED_DESCENDING

            else -> StorageConstants.ORDER_BY_NAME_ASCENDING
        }
    }
}