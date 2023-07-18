package com.example.marvelapp.framework.network.response

import com.example.domain.model.Event
import com.google.gson.annotations.SerializedName

data class EventResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
)

fun EventResponse.toEventModel(): Event {
    return Event(
        id = id,
        imageUrl = thumbnail.imageUrl

    )
}
