package com.example.bookshelfapp.network

import com.example.bookshelfapp.model.BookShelf
import retrofit2.http.GET

interface BookApiService {
    @GET("books/v1/volumes?q=jazz+history")
    suspend fun getBooks(): BookShelf
}