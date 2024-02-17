package com.example.bookshelfapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class BookShelf(
//    @SerialName("items")
//    @Serializable
    val items: List<Items>
)