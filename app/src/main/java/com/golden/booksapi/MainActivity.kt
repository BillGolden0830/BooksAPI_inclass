package com.golden.booksapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.golden.booksapi.model.remote.BookNetwork
import com.golden.booksapi.model.remote.BookResponse
import com.golden.booksapi.view.Communicator
import com.golden.booksapi.view.DisplayVerticalFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun doSearch(bookTitle: String, filter: String, bookType: String, maxResults: Int) {
        BookNetwork.bookAPI.getBooksByFilters(
            bookTitle,
            filter,
            maxResults,
            bookType
        ).enqueue(
            object: Callback<BookResponse> {
                override fun onResponse(
                    call: Call<BookResponse>,
                    response: Response<BookResponse>
                ) {
                    if (response.isSuccessful){
                        val body = response.body()
                        createDisplayFragment(body)
                    }
                }

                override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            }
        )
        }

    private fun createDisplayFragment(body: BookResponse?) {
        body?.let{
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DisplayVerticalFragment.newInstance(it))//removes previous fragments, then adds the new one
                //.add()- used to overlap multiple fragments; previous fragments are not destroyed
                //.hide()- makes a fragment invisible
                //.remove()-destroys the fragment
                .commit()
        }
    }
}