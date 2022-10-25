package com.golden.booksapi.model.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//https://www.googleapis.com/books/v1/volumes?q=

interface BookApi {
    @GET(BookNetwork.ENDPOINT)
    fun getBooksByFilters(
        @Query("q") bookTitle: String,
        @Query("filter") bookFilter: String,
        @Query("maxResults") bookMaxResults: Int,
        @Query("printType") bookPrintType: String
    ): Call<BookResponse>
}