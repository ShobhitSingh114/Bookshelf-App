package com.example.bookshelfapp.data

import com.example.bookshelfapp.model.BookShelf
import com.example.bookshelfapp.network.BookApiService

interface BooksRepository {
    suspend fun getBooks(): BookShelf
}

class DefaultBookRepository(private val bookApiService: BookApiService): BooksRepository {
    override suspend fun getBooks(): BookShelf {
        return bookApiService.getBooks()
    }

}