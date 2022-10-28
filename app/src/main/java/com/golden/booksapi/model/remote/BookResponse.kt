package com.golden.booksapi.model.remote

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class BookResponse(
    val items: List<BookItem>
): Parcelable

@Parcelize
data class BookItem(
    val volumeInfo: BookInfo
): Parcelable

@Parcelize
data class BookInfo(
    val title: String = "",
    val subtitle: String = "",
    val authors: List<String> = emptyList(),// List<String>(10) {"Alan Smithee"},
    val description: String = "",
    val publishedDate: String = "",
    val imageLinks: BookImage = BookImage("","")

): Parcelable

data class BookImage(
    val smallThumbnail: String = "",
    val thumbnail: String = ""
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readString()?:""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(smallThumbnail)
        parcel.writeString(thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookImage> {
        override fun createFromParcel(parcel: Parcel): BookImage {
            return BookImage(parcel)
        }

        override fun newArray(size: Int): Array<BookImage?> {
            return arrayOfNulls(size)
        }
    }
}
