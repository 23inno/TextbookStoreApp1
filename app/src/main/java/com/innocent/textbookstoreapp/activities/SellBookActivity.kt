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
        val etBookPrice = findViewById<EditText>(R.id.etBookPrice)
        val btnAddBook = findViewById<Button>(R.id.btnAddBook)

        btnAddBook.setOnClickListener {
            val title = etBookTitle.text.toString().trim()
            val price = etBookPrice.text.toString().trim()

            if (title.isEmpty()) {
                etBookTitle.error = "Enter book title"
                return@setOnClickListener
            }

            if (price.isEmpty()) {
                etBookPrice.error = "Enter book price"
                return@setOnClickListener
            }

            val resultIntent = Intent()
            resultIntent.putExtra("bookTitle", title)
            resultIntent.putExtra("bookPrice", price)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}