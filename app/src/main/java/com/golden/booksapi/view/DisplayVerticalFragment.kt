package com.golden.booksapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.golden.booksapi.databinding.DisplayFragmentLayoutBinding
import com.golden.booksapi.model.remote.BookInfo
import com.golden.booksapi.model.remote.BookResponse
import com.golden.booksapi.view.adapter.BookAdapter
import parseBookList


class DisplayVerticalFragment: Fragment() {

    companion object{
        const val DISPLAY_BOOK = "DISPLAY_BOOK"

        fun newInstance(bookResponse:BookResponse): DisplayVerticalFragment{
            val fragment = DisplayVerticalFragment()
            val bundle = Bundle()
            bundle.putParcelable(DISPLAY_BOOK, bookResponse)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: DisplayFragmentLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DisplayFragmentLayoutBinding.inflate(inflater,container,false)

        initViews()

        arguments?.getParcelable<BookResponse>(DISPLAY_BOOK)?.let{
            updateAdapter(it)
        }
        return binding.root
    }

    private fun initViews() {
        binding.rvBookView.layoutManager = LinearLayoutManager(context)

        //binding.rvBookView.adapter = BookAdapter(emptyList()){
        // Trailing lambda

    }


    private fun updateAdapter(dataSet: BookResponse) {
        binding.rvBookView.adapter = BookAdapter(parseListBo0okInfo(dataSet)) {
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun parseListBo0okInfo(dataSet: BookResponse): List<BookInfo> {
        return dataSet.items.parseBookList()

        }



        fun navigateDetails(bookInfo: BookInfo) {

    }
}