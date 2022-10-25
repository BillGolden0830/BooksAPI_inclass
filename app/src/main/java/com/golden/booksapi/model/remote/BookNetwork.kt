package com.golden.booksapi.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * In Kotlin, this  is a singleton, an object that exists in memory/
 * does not need an instance/constructor
 * Static reference.
 */

object BookNetwork {
/* Retrofit
    1. Create an API interface
    2. Define HTTP verbs in the interface
    3. Define Base URL, endpoints
    4. Create Retrofit object
    5. Create API INTERFACE
 */
    const val BASE_URL = "https://www.googleapis.com"
    const val ENDPOINT = "books/v1/volumes/"

    val bookAPI: BookApi by lazy {
        initRetrofit().create(BookApi::class.java)
    }

    private fun initRetrofit(): Retrofit{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}