package com.golden.booksapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.golden.booksapi.R
import com.golden.booksapi.databinding.DisplayFragmentLayoutBinding
import com.golden.booksapi.model.remote.BookResponse
import com.golden.booksapi.view.adapter.BookAdapter
import com.golden.booksapi.view.placeholder.PlaceholderContent
import com.google.android.material.snackbar.Snackbar
import parseBookList

/**
 * A fragment representing a list of Items.
 */
class DisplayHorizontalFragment : Fragment() {

    private var columnCount = 1
    private lateinit var binding: DisplayFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding = DisplayFragmentLayoutBinding.inflate(
                inflater,
            container,
            false
                )

        // Set the adapter
        /*if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyBookItemRecyclerViewAdapter(PlaceholderContent.ITEMS)
            }
        }*/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBookView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        //LET scope function: receive T, return R; unwrap an Optional/Nullable
        arguments?.getParcelable<BookResponse>(ARG_BOOK_RESPONSE)?.also {
            //todo update Adapter
            updateAdapter(it)
        }?: kotlin.run {
            showSnackbar()
        }

//  region SCOPE FUNCTIONS EXPL.
    // APPLY: Pass T, return same object after manipulating
//        //Used to update an instance of a reference or new instance
//        arguments?.getParcelable<BookResponse>(ARG_BOOK_RESPONSE).apply {
//
//        }
//
//        //RUN:
//        arguments?.getParcelable<BookResponse>(ARG_BOOK_RESPONSE)?.run {
//
//        }
//
//        arguments?.getParcelable<BookResponse>(ARG_BOOK_RESPONSE)?.also {
//
//        }
    }

    private fun showSnackbar() {
        Snackbar.make(binding.root, "Unknown error", Snackbar.LENGTH_INDEFINITE)
            .setAction("Dismiss"){
                //todo destroy fragment
            }
            .show()
    }

    private fun updateAdapter(dataset: BookResponse) {
        binding.rvBookView.adapter = BookAdapter(dataset.items.parseBookList()){

        }

    }

    companion object {

        const val ARG_BOOK_POS = "book_selected_index"
        const val ARG_BOOK_RESPONSE = "book_data_set"

        @JvmStatic
        fun newInstance(bookResponse: BookResponse, bookIndex: Int) =
            DisplayHorizontalFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_BOOK_POS, columnCount)
                    putParcelable(ARG_BOOK_RESPONSE, bookResponse)
                }
            }
    }
}