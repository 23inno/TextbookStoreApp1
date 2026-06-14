package com.innocent.textbookstoreapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.innocent.textbookstoreapp.R
import com.innocent.textbookstoreapp.activities.BookDetailsActivity
import com.innocent.textbookstoreapp.model.Book

class BookAdapter(private val bookList: List<Book>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgBook: ImageView = itemView.findViewById(R.id.imgBook)

        val tvBookTitle: TextView =
            itemView.findViewById(R.id.tvBookTitle)

        val tvBookAuthor: TextView =
            itemView.findViewById(R.id.tvBookAuthor)

        val tvBookEdition: TextView =
            itemView.findViewById(R.id.tvBookEdition)

        val tvBookPrice: TextView =
            itemView.findViewById(R.id.tvBookPrice)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)

        return BookViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: BookViewHolder,
        position: Int
    ) {

        val currentBook = bookList[position]

        holder.tvBookTitle.text = currentBook.title
        holder.tvBookAuthor.text = currentBook.author
        holder.tvBookEdition.text = currentBook.edition
        holder.tvBookPrice.text = currentBook.price
        holder.imgBook.setImageResource(currentBook.image)

        holder.itemView.setOnClickListener {

            val intent = Intent(
                holder.itemView.context,
                BookDetailsActivity::class.java
            )

            intent.putExtra("title", currentBook.title)
            intent.putExtra("author", currentBook.author)
            intent.putExtra("edition", currentBook.edition)
            intent.putExtra("price", currentBook.price)
            intent.putExtra("description", currentBook.description)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}