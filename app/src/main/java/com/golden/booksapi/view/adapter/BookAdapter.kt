package com.golden.booksapi.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.golden.booksapi.databinding.BookItemLayoutVerticalBinding
import com.golden.booksapi.model.remote.BookInfo
import com.squareup.picasso3.Picasso


class BookAdapter(private val dataSet: List<BookInfo>,
                  private val openDetails: (BookInfo)-> Unit):RecyclerView.
                    Adapter<BookAdapter.BookViewHolder>() {
    class BookViewHolder(private val binding:BookItemLayoutVerticalBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(bookItem: BookInfo, openDetails: (BookInfo) -> Unit){
                binding.root.setOnClickListener{
                    openDetails(bookItem)
                }
                binding.tvBookTitle.text = bookItem.title
                binding.tvSubtitle.text = bookItem.subtitle
                
                binding.tvBookPublished.text = bookItem.publishedDate


                Picasso.Builder(binding.imgBookCover.context)
                    .build()
                    .load(bookItem.imageLinks.thumbnail)
                    .into(binding.imgBookCover)
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            BookItemLayoutVerticalBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(dataSet[position], openDetails)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}