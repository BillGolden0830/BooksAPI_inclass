package com.golden.booksapi.model.remote

data class BookResponse(
    val items: List<BookItem>
)

data class BookItem(
    val VolumeInfo: BookInfo
)

data class BookInfo(
    val title: String
    val subtitle: String
    val authors: List<String>
    val description: String
    val publishedDate: String
    val imageLinks: BookImage

)

data class BookImage(
    val smallThumbnail: String
    val thumbnail: String
)
