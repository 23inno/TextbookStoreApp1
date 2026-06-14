package com.innocent.textbookstoreapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.innocent.textbookstoreapp.R

class SellBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_book)

        val etBookTitle = findViewById<EditText>(R.id.etBookTitle)
        val etBookAuthor = findViewById<EditText>(R.id.etBookAuthor)
        val etBookEdition = findViewById<EditText>(R.id.etBookEdition)
        val etBookPrice = findViewById<EditText>(R.id.etBookPrice)
        val etBookDescription = findViewById<EditText>(R.id.etBookDescription)
        val btnAddBook = findViewById<Button>(R.id.btnAddBook)

        btnAddBook.setOnClickListener {
            val title = etBookTitle.text.toString().trim()
            val author = etBookAuthor.text.toString().trim()
            val edition = etBookEdition.text.toString().trim()
            val price = etBookPrice.text.toString().trim()
            val description = etBookDescription.text.toString().trim()

            if (title.isEmpty()) {
                etBookTitle.error = "Enter book title"
                return@setOnClickListener
            }

            if (author.isEmpty()) {
                etBookAuthor.error = "Enter author"
                return@setOnClickListener
            }

            if (edition.isEmpty()) {
                etBookEdition.error = "Enter edition"
                return@setOnClickListener
            }

            if (price.isEmpty()) {
                etBookPrice.error = "Enter price"
                return@setOnClickListener
            }

            if (description.isEmpty()) {
                etBookDescription.error = "Enter description"
                return@setOnClickListener
            }

            val resultIntent = Intent()
            resultIntent.putExtra("bookTitle", title)
            resultIntent.putExtra("bookAuthor", author)
            resultIntent.putExtra("bookEdition", edition)
            resultIntent.putExtra("bookPrice", price)
            resultIntent.putExtra("bookDescription", description)

            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}