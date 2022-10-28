package com.golden.booksapi.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.golden.booksapi.MainActivity
import com.golden.booksapi.R
import com.golden.booksapi.databinding.SearchFragmentLayoutBinding
import com.google.android.material.textfield.TextInputLayout

class SearchFragment: Fragment() {

    private lateinit var binding: SearchFragmentLayoutBinding
    private lateinit var bridge: Communicator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context){
            is Communicator -> bridge = context
            else -> throw IllegalAccessError("Incorrect host activity")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SearchFragmentLayoutBinding.inflate(inflater, container, false)

        //Traditional, no binding:
        //val view = inflater.inflate(R.layout.search_fragment, container, attachToRoot: false)
        //view.findViewById<TextInputLayout>(R.id.til_book_search)
        initViews()

        return binding.root
    }

    private fun initViews()
    {
        binding.btnSearch.setOnClickListener {
            bridge.doSearch(
                binding.tilBookSearch.editText?.text.toString(),
                binding.spFilter.selectedItem.toString(),
                binding.spBookType.selectedItem.toString(),
                binding.maxResults.progress
            )
        }
        binding.spFilter.adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            requireContext().resources.getStringArray(R.array.sp_filter)
        )
        context?.let{
            binding.spBookType.adapter = ArrayAdapter<String>(
                it,
                android.R.layout.simple_list_item_1,
                it.resources.getStringArray((R.array.sp_book_type))
            )

        }
    }

}