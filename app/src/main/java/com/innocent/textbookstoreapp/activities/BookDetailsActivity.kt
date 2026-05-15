package com.innocent.textbookstoreapp.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.innocent.textbookstoreapp.R

class BookDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val tvBookName = findViewById<TextView>(R.id.tvBookName)
        val tvBookPrice = findViewById<TextView>(R.id.tvBookPrice)
        val btnContactSeller = findViewById<Button>(R.id.btnContactSeller)

        val bookTitle = intent.getStringExtra("bookTitle")
        val bookPrice = intent.getStringExtra("bookPrice")

        tvBookName.text = bookTitle
        tvBookPrice.text = "Price: $bookPrice"

        btnContactSeller.setOnClickListener {
            Toast.makeText(
                this,
                "Seller Contact Requested",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}