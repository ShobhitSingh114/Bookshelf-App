package com.example.bookshelfapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class Items(
//    @SerialName("id")
    val id: String,
//    @SerialName("volumeInfo")
//    @Serializable
    val volumeInfo: VolumeInfo
)
