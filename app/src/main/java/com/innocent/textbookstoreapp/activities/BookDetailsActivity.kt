package com.innocent.textbookstoreapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.innocent.textbookstoreapp.databinding.ActivityBookDetailsBinding

class BookDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title") ?: ""
        val author = intent.getStringExtra("author") ?: ""
        val edition = intent.getStringExtra("edition") ?: ""
        val price = intent.getStringExtra("price") ?: ""
        val description = intent.getStringExtra("description") ?: ""

        binding.bookTitle = title
        binding.bookAuthor = author
        binding.bookEdition = edition
        binding.bookPrice = price
        binding.bookDescription = description

        binding.btnContactSeller.setOnClickListener {

            val intent = Intent(
                this,
                ContactSellerActivity::class.java
            )

            intent.putExtra("bookTitle", title)

            startActivity(intent)
        }
    }
}