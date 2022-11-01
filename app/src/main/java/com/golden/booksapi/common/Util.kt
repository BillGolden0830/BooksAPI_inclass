import com.golden.booksapi.model.remote.BookInfo
import com.golden.booksapi.model.remote.BookItem

fun List<BookItem>.parseBookList() =

map { bookItem ->
    BookInfo(
        bookItem.volumeInfo.title,
        bookItem.volumeInfo.subtitle,
        bookItem.volumeInfo.authors,
        bookItem.volumeInfo.description,
        bookItem.volumeInfo.publishedDate,
        bookItem.volumeInfo.imageLinks
    )
}