package com.innocent.textbookstoreapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.innocent.textbookstoreapp.R
import com.innocent.textbookstoreapp.activities.BookDetailsActivity
import com.innocent.textbookstoreapp.model.Book

class BookAdapter(private val bookList: List<Book>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvBookTitle: TextView = itemView.findViewById(R.id.tvBookTitle)
        val tvBookPrice: TextView = itemView.findViewById(R.id.tvBookPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)

        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        val currentBook = bookList[position]

        holder.tvBookTitle.text = currentBook.title
        holder.tvBookPrice.text = currentBook.price

        holder.itemView.setOnClickListener {

            val context = holder.itemView.context

            val intent = Intent(context, BookDetailsActivity::class.java)

            intent.putExtra("bookTitle", currentBook.title)
            intent.putExtra("bookPrice", currentBook.price)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}